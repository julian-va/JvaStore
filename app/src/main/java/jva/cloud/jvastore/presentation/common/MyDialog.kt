package jva.cloud.jvastore.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
internal fun MyDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
): Unit {
    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }, content = { content() })
    }
}