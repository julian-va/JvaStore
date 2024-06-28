package jva.cloud.jvastore.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jva.cloud.jvastore.domain.model.Product

@Composable
internal fun AddAndRemoveComponent(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment: Alignment.Vertical,
    addQuantity: (product: Product) -> Unit,
    removeQuantity: (product: Product) -> Unit,
    product: Product
): Unit {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
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