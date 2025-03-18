package com.morozov.bikemaptesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.morozov.bikemaptesttask.data.ApiService
import com.morozov.bikemaptesttask.presentation.screens.LoginScreen
import com.morozov.bikemaptesttask.presentation.screens.LoginViewModel
import com.morozov.bikemaptesttask.presentation.screens.LoginViewModelFactory
import com.morozov.bikemaptesttask.ui.theme.BikemapTestTaskTheme

class MainActivity : ComponentActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val apiService = ApiService()
        val viewModelFactory = LoginViewModelFactory(apiService)
        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        setContent {
            BikemapTestTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = loginViewModel
                    )
                }
            }
        }
    }
}

