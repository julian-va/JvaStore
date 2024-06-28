package jva.cloud.jvastore.presentation.view.storeview.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyTopAppBarStore(
    pinnedScrollBehavior: TopAppBarScrollBehavior,
    cartQuantity: String,
    navigateToCart: () -> Unit
): Unit {
    TopAppBar(title = {
        Text(text = "JVA STORE")
    },
        scrollBehavior = pinnedScrollBehavior,
        actions = {
            BadgedBox(modifier = Modifier.padding(10.dp), badge = {
                Badge(
                    containerColor = Color.Blue,
                    contentColor = Color.Gray,
                    content = { Text(text = cartQuantity) }
                )
            }
            ) {
                IconButton(onClick = { navigateToCart() }) {
                    Icon(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = ""
                    )
                }
            }
        })
}