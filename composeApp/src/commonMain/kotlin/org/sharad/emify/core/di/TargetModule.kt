package org.sharad.emify.core.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.sharad.emify.core.data.local.roomdb.getRoomDB
import org.sharad.emify.core.networking.createHttpClient
import org.sharad.emify.core.util.Prefs
import org.sharad.emify.features.login.presentation.viewmodel.OnboardingViewModel
import org.sharad.emify.features.login.presentation.viewmodel.OtpScreenViewModel
import org.sharad.emify.features.login.presentation.viewmodel.PhoneNumberScreenViewModel
import org.sharad.emify.core.networking.repository.LoginRepository
import org.sharad.emify.core.networking.repository.ProfileRepository
import org.sharad.emify.core.util.AuthTokenNavigation
import org.sharad.emify.features.profile.presentation.Screens.BusinessDetailsScreen
import org.sharad.emify.features.profile.presentation.viewmodels.AddBankAccountViewModel
import org.sharad.emify.features.profile.presentation.viewmodels.BusinessScreenViewModel
import org.sharad.emify.features.profile.presentation.viewmodels.KycScreenViewModel
import org.sharad.emify.features.profile.presentation.viewmodels.ReportScreenViewModel

expect val targetModule: Module

val sharedModule= module{
    single { getRoomDB(get()) }
    single { createHttpClient(get()) }
    single { Prefs(get()) }
    single { AuthTokenNavigation(get()) }
    single { LoginRepository(get(),get()) }
    single { ProfileRepository(get(), get()) }
    viewModel{PhoneNumberScreenViewModel(get(),get())}
    viewModel{ OtpScreenViewModel(get(),get(),get())}
    viewModel{ OnboardingViewModel(get(),get()) }
    viewModel { BusinessScreenViewModel() }
    viewModel { ReportScreenViewModel() }
    viewModel { AddBankAccountViewModel() }
    viewModel { KycScreenViewModel() }
}

fun initializeKoin(
    config: (KoinApplication.()-> Unit)?=null
){
    startKoin {
        config?.invoke(this)
        modules(
            listOf(
                targetModule,
                sharedModule
            )
        )
    }
}