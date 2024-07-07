package jva.cloud.jvastore.presentation.view.cartview.viewmodel

import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE
import jva.cloud.jvastore.util.ConstantApp.STRING_EMPTY
import jva.cloud.jvastore.util.ConstantApp.ZERO

data class CarViewModelState(
    val products: List<Product> = listOf(),
    val address: String = STRING_EMPTY,
    val totalCartPay: Int = ZERO,
    val totalCartProduct: Int = ZERO,
    val progressIndicator: Boolean = BOOLEAN_TRUE
)
