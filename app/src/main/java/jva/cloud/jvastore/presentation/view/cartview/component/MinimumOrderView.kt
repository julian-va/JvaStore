package jva.cloud.jvastore.presentation.view.cartview.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun MinimumOrderView(
    modifier: Modifier,
    text: String,
    showIcon: Boolean,
    contentAlignment: Alignment
): Unit {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment

    ) {
        if (showIcon) MyTextIcon(text = text) else MyTextNotIcon(text = text)
    }
}

@Composable
private fun MyTextNotIcon(text: String) {
    Text(
        textAlign = TextAlign.Center,
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.background,
        fontWeight = FontWeight.Bold,
        maxLines = 2
    )
}

@Composable
private fun MyTextIcon(text: String) {
    Row {
        Icon(imageVector = Icons.Default.Done, contentDescription = "")
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            textAlign = TextAlign.Center,
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.scrim,
            fontWeight = FontWeight.Bold,
            maxLines = 2
        )
    }
}