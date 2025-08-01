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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
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
import emify.composeapp.generated.resources.info_icon
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.core.ui.theme.appGray
import org.sharad.emify.core.ui.theme.grayBorder
import org.sharad.emify.features.login.presentation.viewmodel.OnboardingViewModel
import org.sharad.emify.features.login.presentation.viewmodel.OtpScreenViewModel

@Composable
fun OnboardingScreen(navController: NavController){

    val viewModel: OnboardingViewModel = koinViewModel()
    val firstName by viewModel.firstName.collectAsStateWithLifecycle()
    val lastName by viewModel.lastName.collectAsStateWithLifecycle()

    val isEnabled by viewModel.isEnabled.collectAsStateWithLifecycle()

    val focusRequester= remember { FocusRequester() }
    val focusManager= LocalFocusManager.current


    Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
    {
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.95f).padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp))
            {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
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

                    Surface(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .size(48.dp),
                        shape = CircleShape,
                        color = Color.White,
                        shadowElevation = 4.dp
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .clickable(onClick = {
                                    navController.popBackStack()
                                }),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.back_arrow),
                                contentDescription = "back",
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Enter your details",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "Enter your information to start using the EMIfy App",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.displayLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        "Full Name",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = firstName,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusRequester.requestFocus()
                                }
                            ),
                            onValueChange = { viewModel.updateFirstName(it) },
                            maxLines = 1,
                            modifier = Modifier.height(56.dp).weight(1f),
                            placeholder = {
                                Text(
                                    "First Name", fontFamily = Poppins,
                                    fontSize = 16.sp,
                                    color = appGray.copy(0.5f)
                                )
                            },
                            shape = RoundedCornerShape(14.dp),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = grayBorder,
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = Poppins,
                                color = MaterialTheme.colorScheme.onBackground
                            ),
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { viewModel.updateLastName(it) },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                            maxLines = 1,
                            modifier = Modifier.height(56.dp).weight(1f).focusRequester(focusRequester),
                            placeholder = {
                                Text(
                                    "Last Name", fontFamily = Poppins,
                                    fontSize = 16.sp,
                                    color = appGray.copy(0.5f)
                                )
                            },
                            shape = RoundedCornerShape(14.dp),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = grayBorder,
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = Poppins,
                                color = MaterialTheme.colorScheme.onBackground
                            ),
                            singleLine = true,
                        )
                    }
                }
                Spacer(modifier=Modifier.height(4.dp))
                Row {
                    Image(
                        painter = painterResource(Res.drawable.info_icon),
                        contentDescription = "info",
                        modifier = Modifier.padding(horizontal = 8.dp).size(24.dp)
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text="Please specify your name as per your Government Identification.",
                        style = MaterialTheme.typography.displayMedium
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

                    },
                    enabled = isEnabled
                )
            }
        }
    }
}