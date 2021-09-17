package com.yogadarma.core.data.source.remote.network

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val error: Throwable? = null, val message: String? = null) :
        ApiResponse<Nothing>()
}
