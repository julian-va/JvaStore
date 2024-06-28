package jva.cloud.jvastore.presentation.view.storeview.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.domain.model.Product

@Composable
internal fun BodyStore(
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
            ProductCardStore(product = product,
                image = imageStr,
                goToStoreDetail = { storeDetail(it) },
                addQuantity = { addQuantity(it) },
                removeQuantity = { removeQuantity(it) })
        }
    }
}
