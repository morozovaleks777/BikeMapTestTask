package com.morozov.bikemaptesttask.domain

interface AuthRepository {
    suspend fun login(email: String, password: String): ApiResponse<Int?>
}