package org.sharad.emify.core.navigation

import kotlinx.serialization.Serializable
import org.sharad.emify.features.mandateCreation.presentation.viewModels.AmountCollectionMethod
import org.sharad.emify.features.mandateCreation.presentation.viewModels.BillingFrequency
import org.sharad.emify.features.mandateCreation.presentation.viewModels.WholeBillingFrequency

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

@Serializable
data object Routes_AboutEmifyMenu

@Serializable
data object Routes_HelpNSupport

@Serializable
data object Routes_AboutUs

@Serializable
data object Routes_GreivancePolicy

@Serializable
data object Routes_PrivacyPolicy

@Serializable
data object Routes_TnC

@Serializable
data object Routes_FAQ

@Serializable
data object Routes_Guide

@Serializable
data object Routes_Report

@Serializable
data object Routes_BankAccountScreen
@Serializable
data object Routes_AddBankAccountScreen

@Serializable
data object Routes_KYCScreen


@Serializable
data object Routes_EPansScreen
@Serializable
data object Routes_RNEScreen

@Serializable
data object Routes_MandateCreationScreen

@Serializable
data class Routes_SubscriptionMandateScreen(
    val name:String,
    val phone:String,
    val purpose:String,
    val amount:String
)

@Serializable
data class Routes_WholeEmiMandateScreen(
    val name:String,
    val phone:String,
    val purpose:String,
    val amount:String
)

@Serializable
data class Routes_ConfirmSubscriptionMandateScreen(
    val name: String,
    val phone: String,
    val purpose: String,
    val amount: String,
    val duration: Int,
    val date: Long,
    val frequency: BillingFrequency,
)

@Serializable
data class Routes_ConfirmWholeMandateScreen(
    val name: String,
    val phone: String,
    val purpose: String,
    val amount: String,
    val installmentsNumber: Int? = null,
    val installmentDate: Long? = null,
    val otpCollectionDate: Long? = null,
    val installmentFrequency: WholeBillingFrequency? = null,
    val paymentType: AmountCollectionMethod
)

@Serializable
data object Routes_AllMandateScreen

@Serializable
data class Routes_MandateDetailsScreen(val mandateId: String)

//@Serializable
//data object Routes_SubscriptionMandateScreen


