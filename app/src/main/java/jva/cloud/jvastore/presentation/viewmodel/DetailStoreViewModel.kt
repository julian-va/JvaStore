package jva.cloud.jvastore.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.jvastore.data.local.repository.ProductsLocalRepository
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.model.productToProductsEntity
import jva.cloud.jvastore.domain.usecase.RetrieveProductById
import jva.cloud.jvastore.presentation.viewmodel.state.DetailStoreViewModelState
import jva.cloud.jvastore.util.ConstantApp
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE
import jva.cloud.jvastore.util.ConstantApp.ZERO
import jva.cloud.jvastore.util.ConstantApp.ZERO_STR
import jva.cloud.jvastore.util.UtilsApp.reprocessImageFromApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailStoreViewModel @Inject constructor(
    private val retrieveProductById: RetrieveProductById,
    private val productsLocalRepository: ProductsLocalRepository
) :
    ViewModel() {
    private val _state = mutableStateOf(DetailStoreViewModelState(product = null))
    val state = _state

    fun retrieveProduct(id: String?): Unit {
        if (!state.value.theViewmodelWasAlreadyCalled) {
            viewModelScope.launch(context = Dispatchers.IO) {
                val product =
                    id?.let { idSearch -> retrieveProductById.getProductById(id = idSearch) }
                product?.let { productNotNull ->
                    _state.value = _state.value.copy(product = productNotNull)
                }
            }
        }
        _state.value =
            _state.value.copy(theViewmodelWasAlreadyCalled = ConstantApp.BOOLEAN_TRUE)
    }

    fun reprocessImage(listImage: List<String>): String {
        return reprocessImageFromApi(images = listImage)
    }

    fun changeQuantity(quantity: String) {
        val quantitySte =
            if (quantity.isBlank() || ZERO_STR == quantity) ZERO else quantity.toInt()
        _state.value = _state.value.copy(selectedQuantity = quantitySte)
    }

    fun addToCart(product: Product) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val productEntity = productToProductsEntity(model = product)
            productEntity.selected = BOOLEAN_TRUE
            productEntity.selectedQuantity = state.value.selectedQuantity
            productsLocalRepository.saveAll(listOf(productEntity))
        }
    }
}