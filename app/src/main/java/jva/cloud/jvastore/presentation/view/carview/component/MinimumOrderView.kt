package jva.cloud.jvastore.presentation.view.carview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun MinimumOrderView(text: String, showIcon: Boolean): Unit {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(color = Color.Gray),
        contentAlignment = Alignment.Center

    ) {
        if (showIcon) MyTextIcon(text = text) else MyTextNotIcon(text = text)
    }
}

@Composable
private fun MyTextNotIcon(text: String) {
    Text(
        textAlign = TextAlign.Center,
        text = "you need x value to complete the minimum order",
        maxLines = 2
    )
}

@Composable
private fun MyTextIcon(text: String) {
    Row {
        Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
        Text(
            textAlign = TextAlign.Center,
            text = "you need x value to complete the minimum order",
            maxLines = 2
        )
    }
}