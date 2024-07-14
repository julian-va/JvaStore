package jva.cloud.jvastore.presentation.view.invoicedetailview.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.R
import jva.cloud.jvastore.presentation.common.TextValueCard

@Composable
internal fun MyDeliveryCardInvoiceDetailView(): Unit {
    val delivery = createTest()
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
        //.padding(10.dp)
    ) {
        TextValueCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textHeader = stringResource(R.string.observation),
            text = delivery.observation
        )
        TextValueCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textHeader = stringResource(R.string.delivery_type),
            text = delivery.deliveryType
        )
        TextValueCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textHeader = stringResource(R.string.address),
            text = delivery.address
        )
        TextValueCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textHeader = stringResource(R.string.payment_method),
            text = delivery.paymentMethod
        )
    }
}

private data class Test(
    val address: String,
    val observation: String,
    val deliveryType: String,
    val paymentMethod: String
)

private fun createTest(): Test {
    return Test(
        address = "Cra 20 # 90-07 barrio Cielo",
        paymentMethod = "en efectivo a la hora de la entrega",
        deliveryType = "domicilio puerta de la casa",
        observation = "Ninguna"
    )
}