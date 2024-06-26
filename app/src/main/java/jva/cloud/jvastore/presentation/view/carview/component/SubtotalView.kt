package jva.cloud.jvastore.presentation.view.carview.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubtotalView(): Unit {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Text(text = "Subtotal", modifier = Modifier.padding(end = 120.dp))
                Text(text = "2015489", modifier = Modifier.padding(start = 120.dp))
            }
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp), onClick = { /*TODO*/ }) {
            Text(text = "pay")
        }
    }

}