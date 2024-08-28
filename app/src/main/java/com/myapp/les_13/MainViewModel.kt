package com.myapp.les_13

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val userService: UserService): ViewModel() {

    private val _userResponse: MutableStateFlow<List<User>?> = MutableStateFlow(null)
    val userResponse get() = _userResponse.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()

    fun getUser(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((String) -> Unit)? = null,
    ) {
        viewModelScope.launch {
            _isLoading.emit(true)
            userService.getUsers().onSuccess {
                _isLoading.emit(false)
                _userResponse.emit(it)
            }.onFailure {
                _isLoading.emit(false)
                onFailure?.invoke(it.localizedMessage ?: "")
            }
        }
    }
}