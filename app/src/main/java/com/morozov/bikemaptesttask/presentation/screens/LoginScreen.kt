package com.morozov.bikemaptesttask.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morozov.bikemaptesttask.presentation.components.CustomOutlinedTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel
) {


    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    var showSnackBar by remember { mutableStateOf(false) }

    // Show SnackBar if loginResult is not null and there is a change in the result
    LaunchedEffect(state.loginResult, showSnackBar) {
        if (showSnackBar)
            state.loginResult?.let {
                snackBarHostState.showSnackbar(
                    message = it,
                    duration = SnackbarDuration.Short,
                )
            }
        showSnackBar = false
    }

    Column(modifier = modifier.padding(16.dp)) {
        CustomOutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = "Email",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        CustomOutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = "Password",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.login()
                showSnackBar = true
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text("Login")
        }

        SnackbarHost(hostState = snackBarHostState)
    }
}
