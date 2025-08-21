package org.sharad.emify.features.login.data

import kotlinx.serialization.Serializable

@Serializable
data class UserBusiness(
    val address_line_1: String,
    val address_line_2: String,
    val category: String,
    val city: String,
    val email: String,
    val id: String,
    val landmark: String,
    val name: String,
    val pincode: String,
    val state: String,
    val type: String
)