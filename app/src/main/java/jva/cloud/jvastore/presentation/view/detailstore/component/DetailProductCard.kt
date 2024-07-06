package jva.cloud.jvastore.presentation.view.detailstore.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.presentation.common.MyAsyncImage
import jva.cloud.jvastore.presentation.common.TextValueCard
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE

@Composable
internal fun DetailProductCard(
    quantity: String,
    product: Product,
    image: String,
    addQuantity: (quantity: String) -> Unit,
    addCar: (product: Product) -> Unit,
    modifier: Modifier,
): Unit {
    val dpCustom = (110 - (product.selectedQuantity.toString().length * 4)).dp
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 90.dp, end = 5.dp, start = 5.dp, bottom = 20.dp)
    ) {

        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 3.dp, end = 3.dp)
                .verticalScroll(rememberScrollState())


        ) {

            MyAsyncImage(
                imagePath = image,
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 20.dp, end = 10.dp, start = 10.dp),
                contentScale = ContentScale.Crop
            )
            TextValueCard(textHeader = "product name", text = product.title, isRow = BOOLEAN_FALSE)

            TextValueCard(
                textHeader = "description",
                text = product.description,
                maxLines = 0,
                isRow = BOOLEAN_FALSE
            )
            AddProductDetailStore(
                quantity = quantity,
                product = product,
                addQuantity = { addQuantity(it) },
                addCar = { addCar(it) })
            Spacer(modifier = Modifier.padding(20.dp))
        }
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(dpCustom)
        ) {

            TextValueCard(
                textHeader = "amount",
                text = product.selectedQuantity.toString()
            )
            TextValueCard(
                textHeader = "price",
                text = "$ ${product.price}"
            )
        }
    }
}