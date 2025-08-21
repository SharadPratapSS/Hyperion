package org.sharad.emify.features.login.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthInitiateDto(
    val otp: Otp
)