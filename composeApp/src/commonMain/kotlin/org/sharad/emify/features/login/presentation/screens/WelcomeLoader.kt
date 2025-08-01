package org.sharad.emify.features.login.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import org.sharad.emify.core.navigation.Routes_HomeScreen
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue

@Composable
fun WelcomeLoader(navController: NavHostController) {

    LaunchedEffect(Unit){
        var timer= 6
        while (timer>0){
            delay(1000)
            timer--
        }
        navController.navigate(Routes_HomeScreen)
    }

    Box(modifier= Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            CircularProgressIndicator(
                color = appBlue,
                modifier = Modifier.size(60.dp),
                strokeWidth = 5.dp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Welcome to",
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "EMIfy",
                color = appBlue,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
                lineHeight = 45.sp
            )
        }
    }
}