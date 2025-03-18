package com.morozov.bikemaptesttask.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozov.bikemaptesttask.data.ApiService
import com.morozov.bikemaptesttask.domain.Error.*
import com.morozov.bikemaptesttask.domain.LoginUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


data class LoginState(
    val email: String = "",
    val password: String = "",
    val loginResult: String? = null,
    val isLoading: Boolean = false,
)


class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _state.value = _state.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _state.value = _state.value.copy(password = newPassword)
    }

    fun login() {
        if (_state.value.email.isEmpty() || _state.value.password.isEmpty()) {
            _state.value = _state.value.copy(loginResult = "Email and password cannot be empty")
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val response = loginUseCase.execute(_state.value.email, _state.value.password)
            _state.value = _state.value.copy(
                isLoading = false,
                loginResult = when (response.error) {
                    null -> {
                        "Login successful"
                    }

                    WRONG_CREDENTIALS -> "Wrong credentials"
                    INTERNAL_SERVER_ERROR -> "Internal server error"
                    else -> "Unknown error occurred"
                }
            )
        }
    }
}
