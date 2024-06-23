package jva.cloud.jvastore.presentation.viewmodel.state

import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.STRING_EMPTY
import jva.cloud.jvastore.util.ConstantApp.ZERO

data class StoreViewModelState(
    val theViewmodelWasAlreadyCalled: Boolean = BOOLEAN_FALSE,
    val products: List<Product> = listOf(),
    val productsFilter: List<Product> = listOf(),
    val rememberList: List<Product> = listOf(),
    val keySearchProduct: String = STRING_EMPTY,
    val cartQuantity: Int = ZERO,
)
