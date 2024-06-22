package jva.cloud.jvastore.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.usecase.GetAllProducts
import jva.cloud.jvastore.presentation.viewmodel.state.StoreViewModelState
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE
import jva.cloud.jvastore.util.ConstantApp.STRING_EMPTY
import jva.cloud.jvastore.util.UtilsApp.reprocessImageFromApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val getAllProducts: GetAllProducts) : ViewModel() {
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

    private suspend fun getAllProducts(): Unit {
        val products = getAllProducts.getAll()
        _state.value = _state.value.copy(products = products, rememberList = products)
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
}