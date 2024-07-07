package jva.cloud.jvastore.presentation.view.cartview.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyTopAppBarCarView(
    pinnedScrollBehavior: TopAppBarScrollBehavior,
    navigateToStore: () -> Unit,
    address: String
) {
    TopAppBar(
        modifier = Modifier.padding(start = 8.dp, end = 4.dp),
        scrollBehavior = pinnedScrollBehavior,
        title = { UserInformation(address = address) },
        navigationIcon = {
            IconButton(onClick = { navigateToStore() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        })
}

@Composable
private fun UserInformation(address: String) {
    Column {
        Text(
            text = "Shopping cart",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Row {
            Text(
                text = "User address: ", color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = address.ifEmpty { "User address" },
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}