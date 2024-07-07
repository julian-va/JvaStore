package jva.cloud.jvastore.presentation.view.createuserview.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.jvastore.domain.model.User
import jva.cloud.jvastore.domain.usecase.RetrieveAllUser
import jva.cloud.jvastore.domain.usecase.SaveUser
import jva.cloud.jvastore.util.ConstantApp
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE
import jva.cloud.jvastore.util.ConstantApp.ONE
import jva.cloud.jvastore.util.ConstantApp.STRING_EMPTY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val retrieveAllUser: RetrieveAllUser,
    private val saveUser: SaveUser
) : ViewModel() {
    private val _state = mutableStateOf(CreateUserViewModelState())
    val state = _state

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            getAllUser()
        }
    }

    private suspend fun getAllUser() {
        retrieveAllUser.getAll().collect { users ->
            if (users.isNotEmpty()) {
                viewModelScope.launch(context = Dispatchers.Main) {
                    _state.value = _state.value.copy(redirectUserInformation = BOOLEAN_TRUE)
                }
                return@collect
            }
        }
    }

    fun updateParameterStatus(
        userName: String = STRING_EMPTY,
        email: String = STRING_EMPTY,
        address: String = STRING_EMPTY,
        dni: String = STRING_EMPTY
    ): Unit {
        _state.value = _state.value.copy(
            userName = updateUserAttributeField(
                userName,
                _state.value.userName
            ),
            email = updateUserAttributeField(
                email,
                state.value.email
            ),
            address = updateUserAttributeField(
                address,
                state.value.address
            ),
            dni = updateUserAttributeField(dni, state.value.dni)
        )
    }

    private fun updateUserAttributeField(valueNew: String, valueOld: String): String {
        val setValueNew: Boolean = valueNew.isNotEmpty() || valueOld.length <= ONE
        return if (setValueNew) valueNew else valueOld
    }

    fun createUser(): Unit {
        viewModelScope.launch(context = Dispatchers.IO) {
            val user = User(
                userName = state.value.userName,
                email = state.value.email,
                address = state.value.address,
                dni = state.value.dni,
                id = null
            )

            if (validateAfterSaveEmpty(user = user)) {
                openDialog()
                return@launch
            }
            saveUser.saveAll(models = listOf(user))
        }
    }

    private fun validateAfterSaveEmpty(user: User): Boolean {
        return user.email.isEmpty() || user.userName.isEmpty() || user.dni.isEmpty() || user.address.isEmpty()
    }

    private fun openDialog(): Unit {
        dialogState(show = BOOLEAN_TRUE)
    }

    fun onDialogDismiss(): Unit {
        dialogState(show = ConstantApp.BOOLEAN_FALSE)
    }

    private fun dialogState(show: Boolean): Unit {
        _state.value = _state.value.copy(showDialog = show)
    }
}