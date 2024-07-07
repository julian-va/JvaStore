package jva.cloud.jvastore.presentation.view.cartview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.jvastore.presentation.common.MyCircularProgressIndicator
import jva.cloud.jvastore.presentation.common.NotFoundView
import jva.cloud.jvastore.presentation.view.cartview.component.CartDescription
import jva.cloud.jvastore.presentation.view.cartview.component.MinimumOrderView
import jva.cloud.jvastore.presentation.view.cartview.component.MyTopAppBarCarView
import jva.cloud.jvastore.presentation.view.cartview.component.SubtotalView
import jva.cloud.jvastore.presentation.view.cartview.viewmodel.CarViewModel
import jva.cloud.jvastore.util.ConstantApp
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.MINIMUM_QUANTITY_PRODUCTS

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
                pinnedScrollBehavior = pinnedScrollBehavior, address = state.address
            )
        }) { paddingValues ->
        if (state.progressIndicator) {
            MyCircularProgressIndicator(modifier = Modifier.padding(paddingValues))
            return@Scaffold
        }
        if (state.products.isNotEmpty()) {
            Column(
                modifier = Modifier.padding(paddingValues),
            ) {
                if (state.totalCartProduct < MINIMUM_QUANTITY_PRODUCTS) {
                    MinimumOrderView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .background(color = MaterialTheme.colorScheme.error),
                        text = "you need $MINIMUM_QUANTITY_PRODUCTS value to complete the minimum order",
                        showIcon = BOOLEAN_FALSE,
                        contentAlignment = Alignment.Center
                    )
                }


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
                        .background(color = MaterialTheme.colorScheme.outlineVariant),
                    contentAlignment = Alignment.CenterStart,
                    text = "You have added a total of ${state.totalCartProduct} products",
                    showIcon = ConstantApp.BOOLEAN_TRUE
                )
                Spacer(modifier = Modifier.padding(10.dp))
                SubtotalView(subtotal = state.totalCartPay)
            }
        } else {
            NotFoundView(modifier = Modifier.padding(paddingValues))
        }
    }
}
