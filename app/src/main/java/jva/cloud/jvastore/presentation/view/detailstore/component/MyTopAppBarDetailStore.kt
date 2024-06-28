package jva.cloud.jvastore.presentation.view.detailstore.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyTopAppBarDetailStore(navigateToStore: () -> Unit): Unit {
    TopAppBar(title = {
        Text(text = "JVA STORE")
    }, navigationIcon = {
        IconButton(onClick = { navigateToStore() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = ""
            )
        }
    })
}