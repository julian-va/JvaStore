package jva.cloud.jvastore.presentation.view.createuserview.viewmodel

import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE
import jva.cloud.jvastore.util.ConstantApp.STRING_EMPTY

data class CreateUserViewModelState(
    val showDialog: Boolean = BOOLEAN_FALSE,
    val redirectUserInformation: Boolean = BOOLEAN_FALSE,
    val userName: String = STRING_EMPTY,
    val email: String = STRING_EMPTY,
    val address: String = STRING_EMPTY,
    val dni: String = STRING_EMPTY
)
