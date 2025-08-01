package org.sharad.emify.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sharad.emify.features.home.presentation.screens.HomeScreen
import org.sharad.emify.features.login.presentation.screens.GetStartedScreen
import org.sharad.emify.features.login.presentation.screens.OnboardingScreen
import org.sharad.emify.features.login.presentation.screens.OtpScreen
import org.sharad.emify.features.login.presentation.screens.PhoneNumberScreen
import org.sharad.emify.features.login.presentation.screens.WelcomeLoader

@Composable
fun BaseNavigation(padding: PaddingValues) {
    val navController= rememberNavController()
    NavHost(navController=navController, startDestination = Routes_GetStarted, modifier = Modifier.padding(padding)){
        composable<Routes_GetStarted>{
            GetStartedScreen(navController=navController)
        }
        composable<Routes_PhoneNumberInput>{
            PhoneNumberScreen(navController=navController)
        }
        composable<Routes_OTPInput>{
            OtpScreen(navController)
        }
        composable<Routes_OnboardingForm>{
            OnboardingScreen(navController)
        }
        composable<Routes_WelcomeLoader>{
            WelcomeLoader(navController)
        }
        composable<Routes_HomeScreen>{
            HomeScreen()
        }
    }
}