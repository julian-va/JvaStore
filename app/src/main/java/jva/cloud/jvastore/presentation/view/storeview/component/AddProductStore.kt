package jva.cloud.jvastore.presentation.view.storeview.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.presentation.common.AddAndRemoveComponent

@Composable
internal fun AddProductStore(
    modifier: Modifier,
    product: Product,
    addQuantity: (product: Product) -> Unit,
    removeQuantity: (product: Product) -> Unit
) {
    if (product.selected) {
        AddAndRemoveComponent(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            addQuantity = { addQuantity(it) },
            removeQuantity = { removeQuantity(it) },
            product = product
        )
    } else {
        Button(modifier = modifier.padding(15.dp), onClick = { addQuantity(product) }) {
            Text(text = "Add Product")
        }
    }
}