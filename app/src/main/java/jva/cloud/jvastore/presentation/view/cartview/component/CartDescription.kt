package jva.cloud.jvastore.presentation.view.cartview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.presentation.common.MyAsyncImage

@Composable
internal fun CartDescription(
    products: List<Product>,
    reprocessImage: (List<String>) -> String,
    addQuantity: (Product) -> Unit,
    removeQuantity: (Product) -> Unit,
    removeAllProductCars: () -> Unit
): Unit {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(580.dp)
    ) {
        items(items = products, key = { it -> it.id }) { product ->
            CartCardProduct(
                product = product,
                reprocessImage = { reprocessImage(it) },
                addQuantity = { addQuantity(it) },
                removeQuantity = { removeQuantity(it) },
            )
        }
        item {
            Button(modifier = Modifier
                .padding(start = 120.dp, end = 120.dp)
                .fillMaxWidth(), onClick = { removeAllProductCars() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                Text(text = "Empty cart")
            }
        }

    }

}

@Composable
private fun CartCardProduct(
    product: Product,
    reprocessImage: (List<String>) -> String,
    addQuantity: (Product) -> Unit,
    removeQuantity: (Product) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(top = 5.dp, bottom = 10.dp)

    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyAsyncImage(
                imagePath = reprocessImage(product.images), modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = product.title, maxLines = 2)
                Text(text = product.price.toString())
                Text(text = product.selectedQuantity.toString())
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(color = Color.Transparent),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Row(
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
                }
            }
        }

    }
}

private data class Test(
    val id: Int,
    val precio: Int,
    val nombre: String
)

private fun products() = listOf(
    Test(nombre = "TEST 1", precio = 52200, id = 1),
    Test(nombre = "TEST 2", precio = 82200, id = 2),
    Test(nombre = "TEST 3", precio = 152200, id = 3),
    Test(nombre = "TEST 4", precio = 200, id = 4),
    Test(nombre = "TEST 5", precio = 12200, id = 5)
)