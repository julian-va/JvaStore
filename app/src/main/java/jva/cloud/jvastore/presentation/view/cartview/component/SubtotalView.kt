package jva.cloud.jvastore.presentation.view.cartview.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SubtotalView(subtotal: Int, navigateToInvoiceDetail: () -> Unit): Unit {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Text(
                    text = "Subtotal",
                    modifier = Modifier.padding(end = 120.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "$ $subtotal",
                    modifier = Modifier.padding(start = 120.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp), onClick = { navigateToInvoiceDetail() }) {
            Text(text = "pay", fontWeight = FontWeight.Bold)
        }
    }

}