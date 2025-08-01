package org.sharad.emify.features.login.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.flag_in
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.navigation.Routes_OTPInput
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.features.login.presentation.viewmodel.PhoneNumberScreenViewModel

@Composable
fun PhoneNumberScreen(navController: NavHostController) {
    val viewModel: PhoneNumberScreenViewModel = koinViewModel()
    val number by viewModel.number.collectAsStateWithLifecycle()
    val isChecked by viewModel.isChecked.collectAsStateWithLifecycle()
    val isEnabled by viewModel.isEnabled.collectAsStateWithLifecycle()


    val interactionText= buildAnnotatedString {
        append("By giving your information, you agree to our ")

        withLink(
            LinkAnnotation.Clickable(
                "TermsConditions",
                linkInteractionListener = {

                }
            )
        ) {
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Medium
                )
            ) {
                withStyle(
                    style = SpanStyle(
                        color = appBlue
                    )
                ){
                    append("Terms & Conditions")
                }
            }
        }

        append(" and ")

        withLink(
            LinkAnnotation.Clickable(
                "PrivacyPolicy",
                linkInteractionListener = {

                }
            )
        ){
            withStyle(
                style = SpanStyle(
                    color = appBlue
                )
            ){
                append("Privacy Policy")
            }
        }
        append(".")
    }

    Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
    {
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.95f).padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp))
            {
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
                Spacer(modifier=Modifier.height(12.dp))
                Text(
                    text = "Enter Your Phone Number",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "Enter your mobile number to start using EMIfy App.",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.displayLarge
                )
                Spacer(modifier=Modifier.height(4.dp))

                Row(
                    modifier = Modifier.height(56.dp).clip(RoundedCornerShape(14.dp))
                        .fillMaxWidth()
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFDCE2EF),
                            shape = RoundedCornerShape(14.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.flag_in),
                        contentDescription = "indiaflag",
                        modifier = Modifier.padding(start = 16.dp).size(28.dp)
                    )
                    Text(
                        text = "+91",
                        fontSize = 18.sp,
                        fontFamily = Poppins,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    VerticalDivider(
                        modifier = Modifier.fillMaxHeight()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        thickness = 1.dp,
                        color = Color(0xFFDCE2EF),
                    )

                    BasicTextField(
                        value = number,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Poppins,
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                        onValueChange = { it ->
                            if (it.length <= 10)
                                viewModel.updateNumber(it)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 2.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (number.isEmpty()) {
                                Text(
                                    text = "XXX-XXXX-XXX",
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                            }
                            it()
                        }
                    }
                }
            }
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)){
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = {
                            viewModel.updateChecked()
                        },
                        modifier = Modifier.size(26.dp)
                    )
                    Text(
                        text=interactionText,
                        style = MaterialTheme.typography.displayMedium
                    )
                }

                BottomButton(
                    text = "Continue",
                    onClick = {
                        navController.navigate(Routes_OTPInput)
                    },
                    enabled = isEnabled
                )
            }
        }
    }
}

