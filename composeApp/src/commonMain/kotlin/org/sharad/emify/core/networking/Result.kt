package org.sharad.emify.core.networking

sealed class NetworkResponse<out T, out E> {
    data class Success<out T>(val data: T): NetworkResponse<T, Nothing>()
    data class Error<out E: org.sharad.emify.core.networking.Error>(val error: E): NetworkResponse<Nothing, E>()
}