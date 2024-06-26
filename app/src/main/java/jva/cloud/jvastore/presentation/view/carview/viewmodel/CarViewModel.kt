package jva.cloud.jvastore.presentation.view.carview.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(CarViewModelState())
    val state = _state
}