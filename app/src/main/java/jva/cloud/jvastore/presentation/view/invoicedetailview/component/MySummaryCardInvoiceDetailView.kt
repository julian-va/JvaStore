package jva.cloud.jvastore.presentation.view.invoicedetailview.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.R
import jva.cloud.jvastore.presentation.common.TextValueCard

@Composable
fun MySummaryCardInvoiceDetailView(): Unit {
    val summary = createTest()
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextValueCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textHeader = stringResource(R.string.subtotal),
            text = summary.subtotal.toString()
        )
        TextValueCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textHeader = stringResource(R.string.tax),
            text = summary.tax.toString()
        )
        TextValueCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textHeader = stringResource(R.string.deliveryCost),
            text = summary.deliveryCost.toString()
        )
        TextValueCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textHeader = stringResource(R.string.discounts),
            text = summary.discounts.toString()
        )
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        TextValueCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textHeader = stringResource(R.string.total),
            text = summary.total.toString()
        )
    }
}

private data class TestS(
    val subtotal: Long,
    val tax: Long,
    val deliveryCost: Long,
    val discounts: Long,
    val total: Long
)

private fun createTest(): TestS {
    return TestS(
        subtotal = 12345897,
        discounts = 0,
        deliveryCost = 2000,
        tax = 1233,
        total = 123456789
    )
}