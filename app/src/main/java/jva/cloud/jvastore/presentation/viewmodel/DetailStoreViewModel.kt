package jva.cloud.jvastore.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.usecase.RetrieveProductFromLocalById
import jva.cloud.jvastore.domain.usecase.SaveProductsLocal
import jva.cloud.jvastore.presentation.viewmodel.state.DetailStoreViewModelState
import jva.cloud.jvastore.util.ConstantApp
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE
import jva.cloud.jvastore.util.ConstantApp.ZERO
import jva.cloud.jvastore.util.ConstantApp.ZERO_STR
import jva.cloud.jvastore.util.UtilsApp.reprocessImageFromApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailStoreViewModel @Inject constructor(
    private val retrieveProductFromLocalById: RetrieveProductFromLocalById,
    private val saveProductsLocal: SaveProductsLocal
) :
    ViewModel() {
    private val _state = mutableStateOf(DetailStoreViewModelState(product = null))
    val state = _state

    fun retrieveProduct(id: String?): Unit {
        if (!state.value.theViewmodelWasAlreadyCalled) {
            viewModelScope.launch(context = Dispatchers.Main) {
                retrieveProductLocal(id = id)
            }
        }
    }

    private suspend fun retrieveProductLocal(id: String?) {
        val product =
            id?.let { idSearch -> retrieveProductFromLocalById.getById(id = idSearch.toInt()) }
        _state.value = _state.value.copy(
            product = product,
            theViewmodelWasAlreadyCalled = ConstantApp.BOOLEAN_TRUE
        )
    }

    fun reprocessImage(listImage: List<String>): String {
        return reprocessImageFromApi(images = listImage)
    }

    fun changeQuantity(quantity: String) {
        val quantitySte =
            if (quantity.isBlank() || ZERO_STR == quantity) ZERO else quantity.trim().toInt()
        _state.value = _state.value.copy(selectedQuantity = quantitySte)
    }

    fun addToCart(product: Product) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val productDrafter = product.copy(
                selected = if (product.selectedQuantity > ZERO) BOOLEAN_TRUE else BOOLEAN_FALSE,
                selectedQuantity = state.value.selectedQuantity
            )
            saveProductsLocal.saveAll(products = listOf(productDrafter))
            _state.value =
                _state.value.copy(product = retrieveProductFromLocalById.getById(id = productDrafter.id))
        }
    }
}