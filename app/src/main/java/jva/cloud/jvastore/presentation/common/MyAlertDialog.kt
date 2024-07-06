package jva.cloud.jvastore.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun MyAlertDialog(
    showDialog: Boolean,
    title: String,
    text: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
): Unit {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "ok_message")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "cancel message")
                }
            },
            title = { Text(text = title) },
            text = { Text(text = text) },
            icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "Info") }
        )
    }
}