package com.morozov.bikemaptesttask.domain


open class LoginUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): ApiResponse<Int?> {
        return authRepository.login(email, password)
    }
}
