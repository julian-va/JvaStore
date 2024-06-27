package jva.cloud.jvastore.presentation.view.cartview.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyTopAppBarCarView(
    pinnedScrollBehavior: TopAppBarScrollBehavior,
    navigateToStore: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.padding(start = 8.dp, end = 4.dp),
        scrollBehavior = pinnedScrollBehavior,
        title = { UserInformation() },
        navigationIcon = {
            IconButton(onClick = { navigateToStore() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        actions = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        })
}

@Composable
private fun UserInformation() {
    Column {
        Text(text = "Shopping cart")
        Row {
            Text(text = "User address: ")
            Text(text = "User address")
        }
    }
}