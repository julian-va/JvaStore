package jva.cloud.jvastore.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextValueCard(
    textHeader: String, text: String
) {
    Row {
        Text(
            text = "$textHeader : ",
            modifier = Modifier.padding(2.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(2.dp),
            softWrap = true,
            maxLines = 1
        )
    }
    Spacer(modifier = Modifier.size(size = 2.dp))
}