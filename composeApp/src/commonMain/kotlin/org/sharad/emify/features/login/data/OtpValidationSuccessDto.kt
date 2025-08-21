package org.sharad.emify.features.login.data

import kotlinx.serialization.Serializable

@Serializable
data class OtpValidationSuccessDto(
    val primary_token: String,
    val refresh_token: String
)