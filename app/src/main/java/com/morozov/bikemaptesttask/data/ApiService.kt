package com.morozov.bikemaptesttask.data

import com.morozov.bikemaptesttask.domain.ApiResponse
import com.morozov.bikemaptesttask.domain.Error.*

// This class and method will simulate the login endpoint

open class ApiService {
    fun login(username: String, password: String): ApiResponse<Int?> {
        return when (username) {
            "user" -> {
                ApiResponse(
                    result = 1,
                    error = null
                )
            }

            "wrong" -> {
                ApiResponse(
                    result = null,
                    error = WRONG_CREDENTIALS
                )
            }

            "internal" -> {
                ApiResponse(
                    result = null,
                    error = INTERNAL_SERVER_ERROR
                )
            }

            else -> {
                ApiResponse(
                    result = null,
                    error = UNKNOWN_ERROR
                )
            }
        }
    }
}