package jva.cloud.jvastore.presentation.view.storeview.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.presentation.common.MyAsyncImage
import jva.cloud.jvastore.presentation.common.TextValueCard

@Composable
internal fun ProductCardStore(
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
        AddProductStore(
            modifier = Modifier
                .fillMaxSize(),
            product = product,
            addQuantity = { addQuantity(it) },
            removeQuantity = { removeQuantity(it) }
        )

    }
}