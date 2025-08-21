package org.sharad.emify.features.login.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.get_started_img
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sharad.emify.core.navigation.Routes_PhoneNumberInput
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue

@Composable
@Preview
fun GetStartedScreen(navController: NavHostController) {
    Box(modifier=Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize().padding(top=40.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(color = appBlue)
                    ){
                        append("EMI")
                    }
                    withStyle(
                        style = SpanStyle(color = Color.Black)
                    ){
                        append("fy")
                    }
                },
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 64.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(color = appBlue)
                    ){
                        append("Easy Mandate Implementation")
                    }
                    withStyle(
                        style = SpanStyle(color = Color.Black)
                    ){
                        append("\nFor You")
                    }
                },
                fontFamily = Poppins,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Box(modifier=Modifier.weight(1f), contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(Res.drawable.get_started_img),
                    contentDescription = "Get Started",
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(horizontal = 16.dp)
                )
            }
            Text(
                text = "Collect Money With Ease",
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color(0xFF535353),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(24.dp))
            BottomButton(text="Get Started", onClick = {
                navController.navigate(Routes_PhoneNumberInput)
            }, enabled=true)
            Spacer(Modifier.height(8.dp))
        }
    }

}



