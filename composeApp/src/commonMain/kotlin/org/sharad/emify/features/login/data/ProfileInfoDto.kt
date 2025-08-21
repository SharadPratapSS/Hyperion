package org.sharad.emify.features.login.data

import kotlinx.serialization.Serializable

@Serializable
data class ProfileInfoDto(
    val created_at: String,
    val entity_type: String,
    val first_name: String?,
    val id: String,
    val last_name: String?,
    val profile_img_url: String?,
    val updated_at: String,
    val user_authentication: UserAuthentication,
    val user_business: UserBusiness?,
    val user_device_sessions: List<UserDeviceSession>
)