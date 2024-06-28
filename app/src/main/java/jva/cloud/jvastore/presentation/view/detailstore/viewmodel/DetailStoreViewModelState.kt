package jva.cloud.jvastore.presentation.view.detailstore.viewmodel

import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE
import jva.cloud.jvastore.util.ConstantApp.ZERO

data class DetailStoreViewModelState(
    val theViewmodelWasAlreadyCalled: Boolean = BOOLEAN_FALSE,
    val selected: Boolean = BOOLEAN_FALSE,
    val selectedQuantity: Int = ZERO,
    val product: Product?,
    val progressIndicator: Boolean = BOOLEAN_TRUE
)
