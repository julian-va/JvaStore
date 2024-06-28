package jva.cloud.jvastore.presentation.view.detailstore

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.jvastore.presentation.common.MyCircularProgressIndicator
import jva.cloud.jvastore.presentation.common.NotFoundView
import jva.cloud.jvastore.presentation.view.detailstore.component.DetailStoreBody
import jva.cloud.jvastore.presentation.view.detailstore.component.MyTopAppBarDetailStore
import jva.cloud.jvastore.presentation.view.detailstore.viewmodel.DetailStoreViewModel

@Composable
fun DetailStoreView(
    id: String?,
    navigateToStore: () -> Unit,
    viewModel: DetailStoreViewModel = hiltViewModel()
) {
    viewModel.retrieveProduct(id = id)
    val state = viewModel.state.value
    Scaffold(topBar = { MyTopAppBarDetailStore(navigateToStore = { navigateToStore() }) }) { paddingValues ->
        if (state.progressIndicator) {
            MyCircularProgressIndicator(modifier = Modifier.padding(paddingValues))
            return@Scaffold
        }
        
        if (state.product != null) {
            DetailStoreBody(
                modifier = Modifier.padding(paddingValues),
                quantity = viewModel.state.value.selectedQuantity.toString(),
                product = state.product,
                reprocessImage = { viewModel.reprocessImage(it) },
                addQuantity = { viewModel.changeQuantity(it) },
                addCar = { viewModel.addToCart(it) })
        } else {
            NotFoundView(modifier = Modifier.padding(paddingValues))
        }

    }
}
