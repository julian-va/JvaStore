package jva.cloud.jvastore.presentation.view.invoicedetailview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jva.cloud.jvastore.presentation.common.MyGenericTopAppBar
import jva.cloud.jvastore.presentation.view.invoicedetailview.component.MyBodyInvoiceDetailView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceDetailView(navigateToCart: () -> Unit, navigateToStore: () -> Unit): Unit {
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyGenericTopAppBar(
                pinnedScrollBehavior = pinnedScrollBehavior,
                navigateTo = { navigateToCart() })
        }) { paddingValues ->
        MyBodyInvoiceDetailView(
            modifier = Modifier.padding(paddingValues),
            navigateToStore = { navigateToStore() })
    }
}
