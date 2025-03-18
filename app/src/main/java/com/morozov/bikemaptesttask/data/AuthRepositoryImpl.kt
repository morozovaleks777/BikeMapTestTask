package com.morozov.bikemaptesttask.data

import com.morozov.bikemaptesttask.domain.ApiResponse
import com.morozov.bikemaptesttask.domain.AuthRepository

class AuthRepositoryImpl(private val apiService: ApiService) : AuthRepository {
    override suspend fun login(email: String, password: String): ApiResponse<Int?> {
        return apiService.login(email, password)
    }
}