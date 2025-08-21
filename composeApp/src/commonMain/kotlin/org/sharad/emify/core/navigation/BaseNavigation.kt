package org.sharad.emify.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.getKoin
import org.sharad.emify.core.util.Prefs
import org.sharad.emify.features.home.presentation.screens.HomeScreen
import org.sharad.emify.features.home.presentation.screens.ProfileDrawer
import org.sharad.emify.features.login.presentation.screens.GetStartedScreen
import org.sharad.emify.features.login.presentation.screens.OnboardingScreen
import org.sharad.emify.features.login.presentation.screens.OtpScreen
import org.sharad.emify.features.login.presentation.screens.PhoneNumberScreen
import org.sharad.emify.features.login.presentation.screens.WelcomeLoader

@Composable
fun BaseNavigation(padding: PaddingValues) {
    val navController= rememberNavController()
    val prefs: Prefs= getKoin().get()
    val userId= prefs.getUserId()
    val isLogin= prefs.getLoginStatus()

    val start=if(userId==null){
        Routes_GetStarted
    }else if(isLogin){
        Routes_HomeScreen
    }else{
        Routes_OnboardingForm
    }

    NavHost(navController=navController, startDestination = start, modifier = Modifier.padding(padding)){
        composable<Routes_GetStarted>{
            GetStartedScreen(navController=navController)
        }
        composable<Routes_PhoneNumberInput>{
            PhoneNumberScreen(navController=navController)
        }
        composable<Routes_OTPInput>(enterTransition = { slideInHorizontally { fullWidth -> fullWidth } },
            exitTransition = { slideOutHorizontally { -it } }){
            val args=it.toRoute<Routes_OTPInput>()
            OtpScreen(navController, number= args.phoneNumber, otpCode=args.otp, userId= args.userId)
        }
        composable<Routes_OnboardingForm>(enterTransition = { slideInHorizontally { fullWidth -> fullWidth } },
            exitTransition = { slideOutHorizontally { -it } }){
            OnboardingScreen(navController)
        }
        composable<Routes_WelcomeLoader>(enterTransition = { slideInHorizontally { fullWidth -> fullWidth } },
            exitTransition = { slideOutHorizontally { -it } }){
            WelcomeLoader(navController)
        }
        composable<Routes_HomeScreen>(enterTransition = { slideInHorizontally { fullWidth -> fullWidth } },
            exitTransition = { slideOutHorizontally { -it } }){
            HomeScreen()
//            ProfileDrawer()
        }
    }
}