package jva.cloud.jvastore.presentation.view.userinformationview.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.jvastore.domain.model.User
import jva.cloud.jvastore.domain.usecase.DeleteUser
import jva.cloud.jvastore.domain.usecase.RetrieveAllUser
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInformationViewModel @Inject constructor(
    private val retrieveAllUser: RetrieveAllUser, private val deleteUser: DeleteUser
) : ViewModel() {
    private val _state = mutableStateOf(UserInformationViewModelState(user = null))
    val state = _state

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            getAllUser()
        }
    }

    private suspend fun getAllUser() {
        retrieveAllUser.getAll().collect { users ->
            if (users.isEmpty()) return@collect
            _state.value = _state.value.copy(user = users.first())
        }
    }

    fun deleteUser(user: User): Unit {
        viewModelScope.launch(context = Dispatchers.IO) {
            deleteUser.delete(model = user)
            viewModelScope.launch(context = Dispatchers.Main) {
                _state.value = _state.value.copy(redirectCreateUser = BOOLEAN_TRUE)
            }
        }
    }
}