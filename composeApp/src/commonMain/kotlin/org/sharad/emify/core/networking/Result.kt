package org.sharad.emify.core.networking


sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: org.sharad.emify.core.networking.Error>(val error: E): Result<Nothing, E>
}
