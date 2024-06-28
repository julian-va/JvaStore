package jva.cloud.jvastore.presentation.view.storeview

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.jvastore.presentation.common.MyCircularProgressIndicator
import jva.cloud.jvastore.presentation.view.storeview.component.BodyStore
import jva.cloud.jvastore.presentation.view.storeview.component.MyTextFieldSearchStore
import jva.cloud.jvastore.presentation.view.storeview.component.MyTopAppBarStore
import jva.cloud.jvastore.presentation.view.storeview.viewmodel.StoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreView(
    navigateToStoreDetail: (String) -> Unit,
    navigateToCart: () -> Unit,
    storeViewModel: StoreViewModel = hiltViewModel()
): Unit {
    val state = storeViewModel.state.value
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(pinnedScrollBehavior.nestedScrollConnection),
        topBar = {
            MyTopAppBarStore(
                pinnedScrollBehavior = pinnedScrollBehavior,
                cartQuantity = state.cartQuantity.toString(),
                navigateToCart = { navigateToCart() },
            )
        }) { paddingValues ->
        
        if (state.progressIndicator) {
            MyCircularProgressIndicator(modifier = Modifier.padding(paddingValues))
            return@Scaffold
        }

        MyTextFieldSearchStore(
            modifier = Modifier.padding(paddingValues),
            searchKey = state.keySearchProduct,
            updateKey = { storeViewModel.searchFilterProducts(it) },
        )

        BodyStore(
            modifier = Modifier.padding(paddingValues),
            products = state.products,
            reprocessImage = { storeViewModel.reprocessImage(it) },
            storeDetail = { navigateToStoreDetail(it) },
            addQuantity = { storeViewModel.addQuantity(it) },
            removeQuantity = { storeViewModel.removeQuantity(it) }
        )
    }
}

