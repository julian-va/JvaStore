package jva.cloud.jvastore.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun NotFoundView(modifier: Modifier) {
    Icon(
        imageVector = Icons.Default.ShoppingCart, contentDescription = "", modifier = modifier
            .fillMaxWidth()
            .height(600.dp)
    )
}