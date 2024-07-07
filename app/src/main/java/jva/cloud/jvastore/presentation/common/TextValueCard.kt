package jva.cloud.jvastore.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE

@Composable
fun TextValueCard(
    modifier: Modifier = Modifier,
    textHeader: String,
    text: String,
    maxLines: Int = 1,
    isRow: Boolean = BOOLEAN_TRUE
) {
    if (isRow) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                text = "$textHeader :",
            )
            Text(
                modifier = Modifier
                    .padding(2.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = if (maxLines > 0) maxLines else Int.MAX_VALUE,
                text = text,
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, top = 10.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                text = textHeader,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.padding(bottom = 10.dp, start = 5.dp, end = 5.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = if (maxLines > 0) maxLines else Int.MAX_VALUE,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify,
                text = text,
            )
        }
    }

    Spacer(modifier = Modifier.size(size = 2.dp))
}