package jva.cloud.jvastore.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.usecase.RetrieveProductsFromLocal
import jva.cloud.jvastore.domain.usecase.SaveProductsLocal
import jva.cloud.jvastore.presentation.viewmodel.state.StoreViewModelState
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE
import jva.cloud.jvastore.util.ConstantApp.ONE
import jva.cloud.jvastore.util.ConstantApp.STRING_EMPTY
import jva.cloud.jvastore.util.ConstantApp.ZERO
import jva.cloud.jvastore.util.UtilsApp.reprocessImageFromApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val retrieveProductsFromLocal: RetrieveProductsFromLocal,
    private val saveProductsLocal: SaveProductsLocal
) : ViewModel() {
    private val _state = mutableStateOf(StoreViewModelState())
    val state = _state

    init {
        if (!state.value.theViewmodelWasAlreadyCalled) {
            viewModelScope.launch(context = Dispatchers.IO) {
                getAllProducts()
            }
        }
        _state.value =
            _state.value.copy(theViewmodelWasAlreadyCalled = BOOLEAN_TRUE)
    }

    private suspend fun getAllProducts() {
        retrieveProductsFromLocal.getAllProducts().collect { products ->
            val quantityOfSelectedProducts =
                products.filter { product -> BOOLEAN_TRUE == product.selected }.size
            _state.value = _state.value.copy(
                products = products,
                rememberList = products,
                cartQuantity = quantityOfSelectedProducts
            )
        }
    }

    fun reprocessImage(listImage: List<String>): String {
        return reprocessImageFromApi(images = listImage)
    }

    fun searchFilterProducts(searchFilter: String = STRING_EMPTY) {

        if (searchFilter.isEmpty()) {
            valueStateSetList(list = state.value.rememberList, saveKey = BOOLEAN_TRUE)
            return
        }

        val listSearch = state.value.rememberList.filter { product ->
            product.title.startsWith(
                prefix = searchFilter,
                ignoreCase = BOOLEAN_TRUE
            )
        }
        valueStateSetList(list = listSearch, key = searchFilter, saveKey = BOOLEAN_TRUE)
    }

    private fun valueStateSetList(
        list: List<Product>,
        key: String = STRING_EMPTY,
        saveKey: Boolean = BOOLEAN_FALSE
    ): Unit {
        if (saveKey) {
            _state.value = _state.value.copy(products = list, keySearchProduct = key)
        } else {
            _state.value = _state.value.copy(products = list)
        }

    }

    fun addQuantity(product: Product) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val quantity = product.selectedQuantity + ONE
            val productDraft = product.copy(selectedQuantity = quantity, selected = quantity > ZERO)
            saveProductsLocal.saveAll(products = listOf(productDraft))
        }
    }

    fun removeQuantity(product: Product) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val quantity = product.selectedQuantity - ONE
            val productDraft = product.copy(selectedQuantity = quantity, selected = quantity > ZERO)
            saveProductsLocal.saveAll(products = listOf(productDraft))
        }
    }
}