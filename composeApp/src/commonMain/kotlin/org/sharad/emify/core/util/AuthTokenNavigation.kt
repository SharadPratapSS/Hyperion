package org.sharad.emify.core.util

import androidx.navigation.NavHostController
import org.sharad.emify.core.navigation.Routes_PhoneNumberInput
import org.sharad.emify.features.login.presentation.screens.PhoneNumberScreen

class AuthTokenNavigation(private val prefs: Prefs) {
    lateinit var navController: NavHostController

    fun initialize(navController: NavHostController) {
        this.navController = navController
    }

    fun navigateToLogin() {
        prefs.setLoginStatus(false)
        prefs.setPrimaryToken(null)
        prefs.setRefreshToken(null)
        prefs.setUserId(null)
        navController.navigate(Routes_PhoneNumberInput){
            popUpTo (0){inclusive=true }
        }
    }
}