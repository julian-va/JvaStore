package jva.cloud.jvastore.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.presentation.common.MyOutlinedTextField
import jva.cloud.jvastore.presentation.common.TextValueCard
import jva.cloud.jvastore.presentation.viewmodel.StoreViewModel

@Composable
fun StoreView(
    navigateToStoreDetail: (String) -> Unit,
    storeViewModel: StoreViewModel = hiltViewModel()
): Unit {
    Scaffold(topBar = {
        MyTopAppBar()
    }) { paddingValues ->

        MyTextFieldSearch(
            modifier = Modifier.padding(paddingValues),
            searchKey = storeViewModel.state.value.keySearchProduct,
            updateKey = { storeViewModel.searchFilterProducts(it) },
        )

        MyBody(
            modifier = Modifier.padding(paddingValues),
            products = storeViewModel.state.value.products,
            reprocessImage = { storeViewModel.reprocessImage(it) },
            storeDetail = { navigateToStoreDetail(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyTopAppBar(): Unit {
    TopAppBar(title = {
        Text(text = "JVA STORE")
    }, actions = {
        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
    })
}

@Composable
private fun MyTextFieldSearch(
    searchKey: String,
    updateKey: (key: String) -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .height(70.dp)
            .fillMaxWidth()
    ) {
        MyOutlinedTextField(
            fieldName = "Search Product",
            text = searchKey,
            imageVector = Icons.Default.Search,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { updateKey(it) })
    }
}

@Composable
private fun MyBody(
    modifier: Modifier,
    products: List<Product>,
    reprocessImage: (listImage: List<String>) -> String,
    storeDetail: (String) -> Unit,
): Unit {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier.padding(top = 80.dp)) {
        items(products, key = { product -> product.id }) { product ->
            val imageStr = reprocessImage(product.images)
            ProductCard(product = product,
                image = imageStr,
                goToStoreDetail = { storeDetail(it) })
        }
    }

}

@Composable
private fun ProductCard(
    product: Product,
    image: String,
    goToStoreDetail: (String) -> Unit
): Unit {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(5.dp)
            .clickable { goToStoreDetail(product.id.toString()) }
    ) {
        AsyncImage(
            model = image,
            contentDescription = ""
        )
        TextValueCard(textHeader = "product name", text = product.title)
        TextValueCard(textHeader = "price", text = product.price.toString())
        TextValueCard(textHeader = "description", text = product.description)
        TextValueCard(textHeader = "category", text = product.category.name)
    }
}