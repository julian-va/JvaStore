package jva.cloud.jvastore.presentation.view.cartview.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.usecase.RetrieveProductsFromLocal
import jva.cloud.jvastore.domain.usecase.SaveProductsLocal
import jva.cloud.jvastore.util.ConstantApp
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.ONE
import jva.cloud.jvastore.util.ConstantApp.ZERO
import jva.cloud.jvastore.util.UtilsApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val saveProductsLocal: SaveProductsLocal,
    private val retrieveProductsFromLocal: RetrieveProductsFromLocal,
) : ViewModel() {
    private val _state = mutableStateOf(CarViewModelState())
    val state = _state

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            getAllProducts()
        }
    }

    private suspend fun getAllProducts() {
        retrieveProductsFromLocal.getAllProducts().collect { products ->
            val productsToPay =
                products.filter { product -> ConstantApp.BOOLEAN_TRUE == product.selected }
            if (productsToPay.isNotEmpty()) {
                _state.value = _state.value.copy(
                    products = productsToPay,
                    totalCartProduct = totalItemsCart(productsTotalItems = productsToPay),
                    totalCartPay = totalPayCart(productsToPay = productsToPay)
                )
                return@collect
            }
            InitialState()
        }
    }

    private fun totalPayCart(productsToPay: List<Product>): Int {
        return productsToPay.map { product -> product.price * product.selectedQuantity }
            .reduce { acc, pay -> acc + pay }
    }

    private fun totalItemsCart(productsTotalItems: List<Product>): Int {
        return productsTotalItems.map { product -> product.selectedQuantity }
            .reduce { acc, itemQuantity -> acc + itemQuantity }
    }

    fun reprocessImage(listImage: List<String>): String {
        return UtilsApp.reprocessImageFromApi(images = listImage)
    }

    fun addQuantity(product: Product) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val quantity = product.selectedQuantity + ONE
            val productDraft =
                product.copy(selectedQuantity = quantity, selected = quantity > ConstantApp.ZERO)
            saveProductsLocal.saveAll(products = listOf(productDraft))
        }
    }

    fun removeQuantity(product: Product) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val quantity = product.selectedQuantity - ONE
            val productDraft =
                product.copy(selectedQuantity = quantity, selected = quantity > ConstantApp.ZERO)
            saveProductsLocal.saveAll(products = listOf(productDraft))
        }
    }

    fun removeAllProductCars() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val products = state.value.products.map { product ->
                product.copy(
                    selectedQuantity = ZERO,
                    selected = BOOLEAN_FALSE
                )
            }
            saveProductsLocal.saveAll(products = products)
        }
    }

    private fun InitialState(): Unit {
        _state.value =
            state.value.copy(totalCartPay = ZERO, totalCartProduct = ZERO, products = listOf())
    }
}