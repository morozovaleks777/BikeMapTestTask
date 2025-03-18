package com.morozov.bikemaptesttask.domain

// Represents a response from the API

data class ApiResponse<T> (
    val result : T?,
    val error : Error?
)