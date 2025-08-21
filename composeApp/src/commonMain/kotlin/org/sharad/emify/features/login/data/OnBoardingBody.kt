package org.sharad.emify.features.login.data

import kotlinx.serialization.Serializable

@Serializable
data class OnBoardingBody(
    val first_name: String,
    val last_name:String?=null
)
