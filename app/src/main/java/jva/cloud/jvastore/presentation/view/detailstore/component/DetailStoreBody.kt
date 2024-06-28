package jva.cloud.jvastore.presentation.view.detailstore.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jva.cloud.jvastore.domain.model.Product

@Composable
internal fun DetailStoreBody(
    modifier: Modifier,
    quantity: String,
    product: Product,
    reprocessImage: (listImage: List<String>) -> String,
    addQuantity: (quantity: String) -> Unit,
    addCar: (product: Product) -> Unit,
) {
    DetailProductCard(
        quantity = quantity,
        product = product,
        image = reprocessImage(product.images),
        addQuantity = { addQuantity(it) }, addCar = { addCar(it) })
}