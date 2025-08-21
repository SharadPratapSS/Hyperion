package org.sharad.emify.features.login.data

import kotlinx.serialization.Serializable

@Serializable
data class OtpValidationBody(
    val code: String,
    val fb_installation_id: String,
    val fcm_token: String,
    val user_id: String,
    val device_name: String
)