package org.sharad.emify.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Routes_GetStarted

@Serializable
data object Routes_PhoneNumberInput

@Serializable
data class Routes_OTPInput(
    val phoneNumber: String,
    val otp: String,
    val userId: String
)

@Serializable
data object Routes_OnboardingForm

@Serializable
data object Routes_WelcomeLoader

@Serializable
data object Routes_HomeScreen

@Serializable
data object Routes_ProfileMenu

@Serializable
data object Routes_BusinessDetails