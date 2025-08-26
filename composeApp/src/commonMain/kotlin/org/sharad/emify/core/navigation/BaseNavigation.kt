package org.sharad.emify.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
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
import org.sharad.emify.features.profile.presentation.Screens.AboutEmifyScreen
import org.sharad.emify.features.profile.presentation.Screens.AboutScreen
import org.sharad.emify.features.profile.presentation.Screens.BusinessDetailsScreen
import org.sharad.emify.features.profile.presentation.Screens.FAQScreen
import org.sharad.emify.features.profile.presentation.Screens.GreivancePolicyScreen
import org.sharad.emify.features.profile.presentation.Screens.GuideScreen
import org.sharad.emify.features.profile.presentation.Screens.HelpSupportScreen
import org.sharad.emify.features.profile.presentation.Screens.PrivacyPolicyScreen
import org.sharad.emify.features.profile.presentation.Screens.ReportScreen
import org.sharad.emify.features.profile.presentation.Screens.TnCScreen

@Composable
fun BaseNavigation(padding: PaddingValues) {
    val navController = rememberNavController()
    val prefs: Prefs = getKoin().get()
    val userId = prefs.getUserId()
    val isLogin = prefs.getLoginStatus()

    val start = if (userId == null) {
        Routes_GetStarted
    } else if (isLogin) {
        Routes_HomeScreen
    } else {
        Routes_OnboardingForm
    }

    NavHost(
        navController = navController,
        startDestination = start,
        modifier = Modifier.padding(padding)
    ) {
        composable<Routes_GetStarted> {
            GetStartedScreen(navController = navController)
        }
        composable<Routes_PhoneNumberInput> {
            PhoneNumberScreen(navController = navController)
        }
        composable<Routes_OTPInput>(
            enterTransition = { slideInHorizontally { fullWidth -> fullWidth } },
            exitTransition = { slideOutHorizontally { -it } }) {
            val args = it.toRoute<Routes_OTPInput>()
            OtpScreen(
                navController,
                number = args.phoneNumber,
                otpCode = args.otp,
                userId = args.userId
            )
        }
        composable<Routes_OnboardingForm>(
            enterTransition = { slideInHorizontally { fullWidth -> fullWidth } },
            exitTransition = { slideOutHorizontally { -it } }) {
            OnboardingScreen(navController)
        }
        composable<Routes_WelcomeLoader>(
            enterTransition = { slideInHorizontally { fullWidth -> fullWidth } },
            exitTransition = { slideOutHorizontally { -it } }) {
            WelcomeLoader(navController)
        }
        composable<Routes_HomeScreen>() {
            HomeScreen(navController)
        }
        composable<Routes_ProfileMenu>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 600
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 500
                    )
                )
            }) {
            ProfileDrawer(navController)
        }
        composable<Routes_BusinessDetails>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            BusinessDetailsScreen(navController)
        }
        composable<Routes_AboutEmifyMenu>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            AboutScreen(navController)
        }

        composable<Routes_AboutUs>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            AboutEmifyScreen(navController)
        }

        composable<Routes_GreivancePolicy>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            GreivancePolicyScreen(navController)
        }


        composable<Routes_PrivacyPolicy>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            PrivacyPolicyScreen(navController)
        }

        composable<Routes_TnC>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            TnCScreen(navController)
        }

        composable<Routes_HelpNSupport>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            HelpSupportScreen(navController)
        }

        composable<Routes_FAQ>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            FAQScreen(navController)
        }

        composable<Routes_Guide>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            GuideScreen(navController)
        }

        composable<Routes_Report>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                ) { fullWidth -> -fullWidth }
            },
            popEnterTransition = { EnterTransition.None},
            exitTransition = { slideOutHorizontally { fullWidth -> fullWidth } },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            }) {
            ReportScreen(navController)
        }

    }
}