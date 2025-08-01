package org.sharad.emify.core.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.sharad.emify.core.data.local.roomdb.getRoomDB
import org.sharad.emify.core.networking.createHttpClient
import org.sharad.emify.features.login.presentation.screens.OnboardingScreen
import org.sharad.emify.features.login.presentation.viewmodel.OnboardingViewModel
import org.sharad.emify.features.login.presentation.viewmodel.OtpScreenViewModel
import org.sharad.emify.features.login.presentation.viewmodel.PhoneNumberScreenViewModel

expect val targetModule: Module

val sharedModule= module{
    single { getRoomDB(get()) }
    single { createHttpClient(get()) }
    viewModel{PhoneNumberScreenViewModel()}
    viewModel{ OtpScreenViewModel()}
    viewModel{ OnboardingViewModel() }
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