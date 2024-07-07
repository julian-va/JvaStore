package jva.cloud.jvastore.presentation.view.invoicedetailview.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.R

@Composable
internal fun MyBodyInvoiceDetailView(modifier: Modifier, navigateToStore: () -> Unit): Unit {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column {
            MyText(text = stringResource(R.string.your_order), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(10.dp))
            MyText(text = stringResource(R.string.delivery), textAlign = TextAlign.Left)
            Spacer(modifier = Modifier.padding(5.dp))
            MyDeliveryCardInvoiceDetailView()
            Spacer(modifier = Modifier.padding(30.dp))
            MyText(text = stringResource(R.string.summary), textAlign = TextAlign.Left)
            Spacer(modifier = Modifier.padding(5.dp))
            MySummaryCardInvoiceDetailView()
            MyButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp), navigateToStore = { navigateToStore() }
            )
        }
    }
}

@Composable
private fun MyText(text: String, textAlign: TextAlign): Unit {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        textAlign = textAlign,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun MyButton(modifier: Modifier, navigateToStore: () -> Unit): Unit {
    Button(modifier = modifier, onClick = { navigateToStore() }) {
        Text(
            text = stringResource(R.string.finalize_order),
        )
    }
}