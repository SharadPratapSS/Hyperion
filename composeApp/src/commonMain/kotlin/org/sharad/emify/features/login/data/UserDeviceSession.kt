package org.sharad.emify.features.login.data

import kotlinx.serialization.Serializable

@Serializable
data class UserDeviceSession(
    val created_at: String,
    val device_name: String,
    val expired: Boolean,
    val expired_at: String?,
    val fb_installations_id: String,
    val fcm_token: String,
    val id: String,
    val updated_at: String,
    val user_id: String
)