package com.morozov.bikemaptesttask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.morozov.bikemaptesttask.presentation.screens.LoginViewModel
import com.morozov.bikemaptesttask.data.ApiService
import com.morozov.bikemaptesttask.domain.LoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // To run LiveData synchronously

    private lateinit var viewModel: LoginViewModel
    private lateinit var apiService: ApiService
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        apiService = mock(ApiService::class.java) // Mocking the ApiService
        loginUseCase = mock(LoginUseCase::class.java) // Mock LoginUseCase
        viewModel = LoginViewModel(loginUseCase) // Initialize the ViewModel with the mock ApiService
    }

    @Test
    fun testEmptyEmailErrorMessage() = runTest {
        // Set the email to an empty string and a password to a non-empty string
        viewModel.onEmailChange("")
        viewModel.onPasswordChange("password123")

        // Call the login function
        viewModel.login()

        // Assert that the error message for empty email is shown
        assertEquals("Email and password cannot be empty", viewModel.state.value.loginResult)
    }
}
