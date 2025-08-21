package org.sharad.emify.features.login.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.back_arrow
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.core.ui.theme.appGray
import org.sharad.emify.features.login.presentation.viewmodel.OtpScreenViewModel

@Composable
fun OtpScreen(navController: NavController, number: String, otpCode: String, userId: String){

    val viewModel: OtpScreenViewModel = koinViewModel()
    val otp by viewModel.otp.collectAsStateWithLifecycle()
    val isEnabled by viewModel.isEnabled.collectAsStateWithLifecycle()
    val loading by viewModel.loader.collectAsStateWithLifecycle()
    val errorMessage by viewModel.error.collectAsStateWithLifecycle()

    var timer by remember{ mutableStateOf(60) }

    var resendEnabled = remember {
        derivedStateOf { timer == 0 }
    }

    LaunchedEffect(Unit){
        while(timer>0){
            delay(1000)
            timer--
        }
    }

    LaunchedEffect(Unit){
        viewModel.updateOtp(otpCode)
    }

    Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
    {
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.95f).padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp))
            {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(color = appBlue)
                            ) {
                                append("EMI")
                            }
                            withStyle(
                                style = SpanStyle(color = Color.Black)
                            ) {
                                append("fy")
                            }
                        },
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        textAlign = TextAlign.Center
                    )

                    Surface(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(48.dp),
                        shape = CircleShape,
                        color = Color.White,
                        shadowElevation = 4.dp
                    ) {
                        Box(modifier=Modifier.fillMaxSize()
                            .clickable(onClick = {
                            navController.popBackStack()
                            }),
                            contentAlignment = Alignment.Center
                        ){
                            Image(
                                painter = painterResource(Res.drawable.back_arrow),
                                contentDescription = "back",
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }

                Spacer(modifier=Modifier.height(12.dp))

                Text(
                    text = "Enter Your Verification Code",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "Enter the OTP sent to your mobile number ($number) to proceed.",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.displayLarge
                )
                Spacer(modifier=Modifier.height(4.dp))

                BasicTextField(
                    value = otp,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Poppins,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    onValueChange = { it ->
                            viewModel.updateOtp(it)
                    },
                    modifier = Modifier
                        .padding(start = 2.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done
                    )
                ) {
                    Row(
                        modifier = Modifier.height(56.dp).clip(RoundedCornerShape(14.dp))
                            .fillMaxWidth()
                            .background(Color.White)
                            .border(
                                width = 1.dp,
                                color = Color(0xFFDCE2EF),
                                shape = RoundedCornerShape(14.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        it()
                    }
                }
                errorMessage?.let {
                    Spacer(modifier=Modifier.height(4.dp))
                    Text(
                        text = "${it.capitalize()} Error. Please Try Again Later",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.Red.copy(alpha = 0.6f)
                    )
                }
                Spacer(modifier=Modifier.height(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text="The OTP is valid for the remaining time of $timer seconds.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium
                )

                TextButton(
                    onClick = {},
                    enabled = resendEnabled.value,
                ){
                   Text(
                       text="Resend Code",
                       style = MaterialTheme.typography.displayMedium,
                       color = if (resendEnabled.value) appBlue else appGray,
                       fontWeight = FontWeight.SemiBold
                   )
                }
            }
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp))
            {
                BottomButton(
                    text = "Continue",
                    onClick = {
                        viewModel.validateOtp(userId,navController)
                    },
                    enabled = isEnabled,
                    showLoader = loading
                )
            }
        }
    }
}