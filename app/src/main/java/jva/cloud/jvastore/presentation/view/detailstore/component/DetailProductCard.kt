package jva.cloud.jvastore.presentation.view.detailstore.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.presentation.common.MyAsyncImage
import jva.cloud.jvastore.presentation.common.TextValueCard

@Composable
internal fun DetailProductCard(
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
                .height(700.dp)
        ) {
            MyAsyncImage(
                imagePath = image, modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp, end = 10.dp, start = 10.dp)
            )
            TextValueCard(textHeader = "product name", text = product.title)
            TextValueCard(textHeader = "price", text = product.price.toString())
            TextValueCard(textHeader = "description", text = product.description)
            TextValueCard(textHeader = "category", text = product.category.name)
            TextValueCard(textHeader = "amount", text = product.selectedQuantity.toString())
            AddProductDetailStore(
                quantity = quantity,
                product = product,
                addQuantity = { addQuantity(it) },
                addCar = { addCar(it) })
        }
    }

}