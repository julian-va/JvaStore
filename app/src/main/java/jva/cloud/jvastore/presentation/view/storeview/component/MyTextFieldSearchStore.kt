package jva.cloud.jvastore.presentation.view.storeview.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.presentation.common.MyOutlinedTextField

@Composable
internal fun MyTextFieldSearchStore(
    searchKey: String,
    updateKey: (key: String) -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .height(70.dp)
            .fillMaxWidth()
    ) {
        MyOutlinedTextField(
            fieldName = "Search Product",
            text = searchKey,
            imageVector = Icons.Default.Search,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { updateKey(it) })
    }
}