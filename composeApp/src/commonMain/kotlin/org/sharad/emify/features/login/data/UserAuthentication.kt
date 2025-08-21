package org.sharad.emify.features.login.data

import kotlinx.serialization.Serializable

@Serializable
data class UserAuthentication(
    val disabled: Boolean,
    val email: String?,
    val id: String,
    val phone: String
)