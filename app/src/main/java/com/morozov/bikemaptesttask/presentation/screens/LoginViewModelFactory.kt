package com.morozov.bikemaptesttask.presentation.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.morozov.bikemaptesttask.data.ApiService

class LoginViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
