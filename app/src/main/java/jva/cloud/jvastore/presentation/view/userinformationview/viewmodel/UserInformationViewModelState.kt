package jva.cloud.jvastore.presentation.view.userinformationview.viewmodel

import jva.cloud.jvastore.domain.model.User
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE

data class UserInformationViewModelState(
    val user: User?,
    val redirectCreateUser: Boolean = BOOLEAN_FALSE
)
