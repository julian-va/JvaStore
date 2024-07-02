package jva.cloud.jvastore.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
        Row {
            Text(
                text = "$textHeader : ",
                modifier = modifier.padding(2.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = text,
                modifier = modifier
                    .padding(2.dp),
                softWrap = true,
                maxLines = if (maxLines > 0) maxLines else Int.MAX_VALUE
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = textHeader,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.padding(bottom = 10.dp),
                text = text,
                maxLines = if (maxLines > 0) maxLines else Int.MAX_VALUE,
                textAlign = TextAlign.Justify,
            )
        }
    }

    Spacer(modifier = Modifier.size(size = 2.dp))
}