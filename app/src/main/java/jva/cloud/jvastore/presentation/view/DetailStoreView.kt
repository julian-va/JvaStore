package jva.cloud.jvastore.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.presentation.common.MyOutlinedTextField
import jva.cloud.jvastore.presentation.common.TextValueCard
import jva.cloud.jvastore.presentation.viewmodel.DetailStoreViewModel
import jva.cloud.jvastore.util.ConstantApp
import jva.cloud.jvastore.util.ConstantApp.STRING_EMPTY

@Composable
fun DetailStoreView(
    id: String?,
    navigateToStore: () -> Unit,
    viewModel: DetailStoreViewModel = hiltViewModel()
) {
    viewModel.retrieveProduct(id = id)
    val state = viewModel.state.value
    Scaffold(topBar = { MyTopAppBarDetail(navigateToStore = { navigateToStore() }) }) { paddingValues ->
        if (state.product != null) {
            MyBody(
                modifier = Modifier.padding(paddingValues),
                quantity = viewModel.state.value.selectedQuantity.toString(),
                product = state.product,
                reprocessImage = { viewModel.reprocessImage(it) },
                addQuantity = { viewModel.changeQuantity(it) },
                addCar = { viewModel.addToCart(it) })
        } else {
            Text(text = "PRODUCTO NULO")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyTopAppBarDetail(navigateToStore: () -> Unit): Unit {
    TopAppBar(title = {
        Text(text = "JVA STORE")
    }, navigationIcon = {
        IconButton(onClick = { navigateToStore() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = ""
            )
        }
    })
}

@Composable
private fun MyBody(
    modifier: Modifier,
    quantity: String,
    product: Product,
    reprocessImage: (listImage: List<String>) -> String,
    addQuantity: (quantity: String) -> Unit,
    addCar: (product: Product) -> Unit,
) {
    ProductCard(
        quantity = quantity,
        product = product,
        image = reprocessImage(product.images),
        addQuantity = { addQuantity(it) }, addCar = { addCar(it) })
}

@Composable
private fun ProductCard(
    quantity: String,
    product: Product,
    image: String,
    addQuantity: (quantity: String) -> Unit,
    addCar: (product: Product) -> Unit,
): Unit {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), contentAlignment = Alignment.Center
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(550.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            )
            TextValueCard(textHeader = "product name", text = product.title)
            TextValueCard(textHeader = "price", text = product.price.toString())
            TextValueCard(textHeader = "description", text = product.description)
            TextValueCard(textHeader = "category", text = product.category.name)
            MyButton(
                quantity = quantity,
                product = product,
                addQuantity = { addQuantity(it) },
                addCar = { addCar(it) })
        }
    }

}

@Composable
private fun MyButton(
    quantity: String,
    product: Product,
    addQuantity: (quantity: String) -> Unit,
    addCar: (product: Product) -> Unit
) {

    val ss = if (quantity == ConstantApp.ZERO_STR) STRING_EMPTY else quantity
    MyOutlinedTextField(
        fieldName = "Add product",
        text = ss,
        imageVector = Icons.Default.ShoppingCart,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { addQuantity(it) })
    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp), onClick = { addCar(product) }) {
        Text(text = "add product")
    }
}

/*@Preview(showSystemUi = true)
@Composable
private fun Preview(): Unit {
    DetailStoreView()
}*/