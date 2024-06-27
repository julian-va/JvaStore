package jva.cloud.jvastore.presentation.view.cartview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.jvastore.presentation.view.cartview.component.CartDescription
import jva.cloud.jvastore.presentation.view.cartview.component.MinimumOrderView
import jva.cloud.jvastore.presentation.view.cartview.component.MyTopAppBarCarView
import jva.cloud.jvastore.presentation.view.cartview.component.SubtotalView
import jva.cloud.jvastore.presentation.view.cartview.viewmodel.CarViewModel
import jva.cloud.jvastore.util.ConstantApp
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(
    navigateToStore: () -> Unit,
    carViewModel: CarViewModel = hiltViewModel()
): Unit {
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val state = carViewModel.state.value
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(pinnedScrollBehavior.nestedScrollConnection),
        topBar = {
            MyTopAppBarCarView(
                navigateToStore = { navigateToStore() },
                pinnedScrollBehavior = pinnedScrollBehavior
            )
        }) { paddingValues ->
        if (state.products.isNotEmpty()) {
            Column(
                modifier = Modifier.padding(paddingValues),
            ) {
                MinimumOrderView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .background(color = Color.Gray),
                    text = "you need x value to complete the minimum order",
                    showIcon = BOOLEAN_FALSE,
                    contentAlignment = Alignment.Center
                )

                CartDescription(
                    products = state.products,
                    removeQuantity = { carViewModel.removeQuantity(it) },
                    addQuantity = { carViewModel.addQuantity(it) },
                    reprocessImage = { carViewModel.reprocessImage(it) },
                    removeAllProductCars = { carViewModel.removeAllProductCars() })
                MinimumOrderView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .background(color = Color.LightGray),
                    contentAlignment = Alignment.CenterStart,
                    text = "You have added a total of ${state.totalCartProduct} products",
                    showIcon = ConstantApp.BOOLEAN_TRUE
                )
                Spacer(modifier = Modifier.padding(10.dp))
                SubtotalView(subtotal = state.totalCartPay)
            }
        } else {
            CartEmpty(modifier = Modifier.padding(paddingValues))
        }

    }

}

@Composable
private fun CartEmpty(modifier: Modifier) {
    Icon(
        imageVector = Icons.Default.ShoppingCart, contentDescription = "", modifier = modifier
            .fillMaxWidth()
            .height(600.dp)
    )
}

/*@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
private fun Preview(): Unit {
    CartView(navigateToStore = {})
}*/