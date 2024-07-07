package jva.cloud.jvastore.presentation.view.cartview.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.usecase.CalculateQuantityAddedProduct
import jva.cloud.jvastore.domain.usecase.RetrieveAllUser
import jva.cloud.jvastore.domain.usecase.RetrieveProductsFromLocal
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE
import jva.cloud.jvastore.util.ConstantApp.ZERO
import jva.cloud.jvastore.util.UtilsApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val retrieveProductsFromLocal: RetrieveProductsFromLocal,
    private val calculateQuantityAddedProduct: CalculateQuantityAddedProduct,
    private val retrieveAllUser: RetrieveAllUser
) : ViewModel() {
    private val _state = mutableStateOf(CarViewModelState())
    val state = _state

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            getAllProducts()
            getAllUser()
        }
    }

    private suspend fun getAllProducts() {
        retrieveProductsFromLocal.getAllProducts().collect { products ->
            val productsToPay =
                products.filter { product -> BOOLEAN_TRUE == product.selected }
            if (productsToPay.isNotEmpty()) {
                _state.value = _state.value.copy(
                    products = productsToPay,
                    totalCartProduct = totalItemsCart(productsTotalItems = productsToPay),
                    totalCartPay = totalPayCart(productsToPay = productsToPay),
                    progressIndicator = BOOLEAN_FALSE
                )
                return@collect
            }
            initialState()
        }
    }

    private suspend fun getAllUser() {
        retrieveAllUser.getAll().collect { users ->
            if (users.isNotEmpty()) {
                _state.value = _state.value.copy(address = users.first().address)
            }
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
            calculateQuantityAddedProduct.addQuantity(product = product)
        }
    }

    fun removeQuantity(product: Product) {
        viewModelScope.launch(context = Dispatchers.IO) {
            calculateQuantityAddedProduct.removeQuantity(product = product)
        }
    }

    fun removeAllProductCars() {
        viewModelScope.launch(context = Dispatchers.IO) {
            calculateQuantityAddedProduct.amountToZero(products = state.value.products)
        }
    }

    private fun initialState(): Unit {
        _state.value =
            state.value.copy(
                totalCartPay = ZERO,
                totalCartProduct = ZERO,
                products = listOf(),
                progressIndicator = BOOLEAN_FALSE
            )
    }

}