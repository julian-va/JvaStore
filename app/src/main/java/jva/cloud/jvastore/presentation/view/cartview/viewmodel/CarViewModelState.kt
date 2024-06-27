package jva.cloud.jvastore.presentation.view.cartview.viewmodel

import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.ZERO

data class CarViewModelState(
    val products: List<Product> = listOf(),
    val totalCartPay: Int = ZERO,
    val totalCartProduct: Int = ZERO,
    val theViewmodelWasAlreadyCalled: Boolean = BOOLEAN_FALSE
)
