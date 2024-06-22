package jva.cloud.jvastore.presentation.viewmodel.state

import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.ZERO

data class DetailStoreViewModelState(
    val theViewmodelWasAlreadyCalled: Boolean = BOOLEAN_FALSE,
    val selected: Boolean = BOOLEAN_FALSE,
    val selectedQuantity: Int = ZERO,
    val product: Product?
)
