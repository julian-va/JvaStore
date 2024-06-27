package jva.cloud.jvastore.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.presentation.common.MyAsyncImage
import jva.cloud.jvastore.presentation.common.MyOutlinedTextField
import jva.cloud.jvastore.presentation.common.TextValueCard
import jva.cloud.jvastore.presentation.viewmodel.StoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreView(
    navigateToStoreDetail: (String) -> Unit,
    navigateToCart: () -> Unit,
    storeViewModel: StoreViewModel = hiltViewModel()
): Unit {
    val state = storeViewModel.state.value
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(pinnedScrollBehavior.nestedScrollConnection),
        topBar = {
            MyTopAppBar(
                pinnedScrollBehavior = pinnedScrollBehavior,
                cartQuantity = state.cartQuantity.toString(),
                navigateToCart = { navigateToCart() },
            )
        }) { paddingValues ->

        MyTextFieldSearch(
            modifier = Modifier.padding(paddingValues),
            searchKey = state.keySearchProduct,
            updateKey = { storeViewModel.searchFilterProducts(it) },
        )

        MyBody(
            modifier = Modifier.padding(paddingValues),
            products = state.products,
            reprocessImage = { storeViewModel.reprocessImage(it) },
            storeDetail = { navigateToStoreDetail(it) },
            addQuantity = { storeViewModel.addQuantity(it) },
            removeQuantity = { storeViewModel.removeQuantity(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyTopAppBar(
    pinnedScrollBehavior: TopAppBarScrollBehavior,
    cartQuantity: String,
    navigateToCart: () -> Unit
): Unit {
    TopAppBar(title = {
        Text(text = "JVA STORE")
    },
        scrollBehavior = pinnedScrollBehavior,
        actions = {
            BadgedBox(modifier = Modifier.padding(10.dp), badge = {
                Badge(
                    containerColor = Color.Blue,
                    contentColor = Color.Gray,
                    content = { Text(text = cartQuantity) }
                )
            }
            ) {
                IconButton(onClick = { navigateToCart() }) {
                    Icon(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = ""
                    )
                }
            }
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
    addQuantity: (product: Product) -> Unit,
    removeQuantity: (product: Product) -> Unit
): Unit {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier.padding(top = 80.dp)) {
        items(products, key = { product -> product.id }) { product ->
            val imageStr = reprocessImage(product.images)
            ProductCard(product = product,
                image = imageStr,
                goToStoreDetail = { storeDetail(it) },
                addQuantity = { addQuantity(it) },
                removeQuantity = { removeQuantity(it) })
        }
    }

}

@Composable
private fun ProductCard(
    product: Product,
    image: String,
    goToStoreDetail: (String) -> Unit,
    addQuantity: (product: Product) -> Unit,
    removeQuantity: (product: Product) -> Unit
): Unit {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { goToStoreDetail(product.id.toString()) }
    ) {
        MyAsyncImage(
            imagePath = image, modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(12.dp)
        )
        TextValueCard(textHeader = "product name", text = product.title)
        TextValueCard(textHeader = "price", text = product.price.toString())
        TextValueCard(textHeader = "description", text = product.description)
        TextValueCard(textHeader = "category", text = product.category.name)
        AddProduct(
            modifier = Modifier
                .fillMaxSize(),
            product = product,
            addQuantity = { addQuantity(it) },
            removeQuantity = { removeQuantity(it) }
        )

    }
}

@Composable
fun AddProduct(
    modifier: Modifier,
    product: Product,
    addQuantity: (product: Product) -> Unit,
    removeQuantity: (product: Product) -> Unit
) {
    if (product.selected) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { removeQuantity(product) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "",
                )
            }

            Text(text = product.selectedQuantity.toString())

            IconButton(onClick = { addQuantity(product) }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
            }
        }
    } else {
        Button(modifier = modifier.padding(15.dp), onClick = { addQuantity(product) }) {
            Text(text = "Add Product")
        }
    }
}