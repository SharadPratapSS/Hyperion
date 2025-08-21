package org.sharad.emify.features.login.data

import kotlinx.serialization.Serializable

@Serializable
data class Otp(
    val code: String,
    val created_at: String,
    val expires_at: String,
    val id: String
)