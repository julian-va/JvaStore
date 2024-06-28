package jva.cloud.jvastore.presentation.view.detailstore.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.usecase.RetrieveProductFromLocalById
import jva.cloud.jvastore.domain.usecase.SaveProductsLocal
import jva.cloud.jvastore.util.ConstantApp
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
                showProgressIndicator(ConstantApp.BOOLEAN_FALSE)
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
        val quantityDraft =
            quantity.replace(Regex("[\\s-,.]"), "")
        val quantitySte =
            if (quantityDraft.isBlank() || ZERO_STR == quantityDraft) ZERO else quantityDraft.trim()
                .toInt()
        _state.value = _state.value.copy(selectedQuantity = quantitySte)
    }

    fun addToCart(product: Product) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val productDrafter = product.copy(
                selected = state.value.selectedQuantity > ZERO,
                selectedQuantity = state.value.selectedQuantity
            )
            saveProductsLocal.saveAll(products = listOf(productDrafter))
            _state.value =
                _state.value.copy(product = retrieveProductFromLocalById.getById(id = productDrafter.id))
        }
    }

    private fun showProgressIndicator(show: Boolean): Unit {
        _state.value = _state.value.copy(progressIndicator = show)
    }
}