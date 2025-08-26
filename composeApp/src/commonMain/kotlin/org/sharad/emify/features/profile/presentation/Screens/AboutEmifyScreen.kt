package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.icon_about
import emify.composeapp.generated.resources.icon_grief
import emify.composeapp.generated.resources.icon_policy
import emify.composeapp.generated.resources.icon_tnc
import org.sharad.emify.core.navigation.Routes_AboutUs
import org.sharad.emify.core.navigation.Routes_GreivancePolicy
import org.sharad.emify.core.navigation.Routes_PrivacyPolicy
import org.sharad.emify.core.navigation.Routes_TnC
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.features.home.presentation.screens.TopBar

@Composable
fun AboutEmifyScreen(navController: NavController){
    Box(modifier= Modifier.fillMaxSize().padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp))
        {
            TopBar("About EMIfy", onBackClick =  {navController.popBackStack()})
            LazyColumn(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)) {
                item {
                    Heading("About Us")
                }
                item {
                    Body("Welcome to EMIFY – your ultimate solution for automated payment collection and reconciliation. At EMIFY, we are revolutionizing the way businesses and vendors manage their payments, making financial workflows effortless, efficient, and reliable.")
                }
                item {
                    Heading("Our Mission")
                }
                item {
                    Body("To empower businesses with cutting-edge tools that simplify payment collections, enhance reconciliation processes, and boost financial transparency. EMIFY is designed to reduce manual intervention, eliminate delays, and provide businesses with more time to focus on growth.")
                }
                item {
                    Heading("Who We Serve")
                }
                item {
                    Body("EMIFY is built for vendors, businesses, and individuals who value seamless payment management. Whether you are a small business owner, a growing enterprise, or a freelancer, our platform is tailored to meet your needs, ensuring a smooth and hassle-free payment experience.")
                }
                item {
                    Heading("What We Offer")
                }
                item {
                    val points = listOf(
                        "Automated Payment Collection: Say goodbye to chasing payments. EMIFY handles it all for you.",
                        "Real-Time Reconciliation: Gain clear insights into your financial transactions with detailed records.",
                        "Secure and Reliable: Your data and payments are safe with industry-leading security standards.",
                        "User-Friendly Interface: A simple and intuitive app designed to work for everyone."
                    )

                    // group them inside Column since one `item {}` must return a single composable
                    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                        points.forEach { point ->
                            BulletPoint(point)
                        }
                    }
                }
                item {
                    Heading("Why Choose EMIFY?")
                }
                item {
                    Body("At EMIFY, we understand the importance of time, accuracy, and efficiency in managing payments. Our platform combines technology and innovation to bring you a solution that doesn’t just manage your payments – it transforms your entire payment process.")
                }
                item {
                    Heading("Join Us")
                }
                item {
                    Body("Thousands of businesses trust EMIFY to handle their payment needs. Join the EMIFY family today and experience the future of payment automation.")
                }
                item {
                    Text(
                        text= buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                fontFamily = Poppins)) {
                                append("EMIFY")
                            }
                            withStyle(style = SpanStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins)) {
                                append(" – Making Payment Reconciliation Effortless.\n" +
                                        "For any queries or support, feel free to reach out to us. We’re here to help!")
                            }
                        }

                    )
                }
            }
        }
    }
}

@Composable
fun Heading(title:String){
    Text(text=title,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        fontFamily = Poppins
        )
}

@Composable
fun Body(body:String){
    Text(text=body,
        fontSize = 14.sp,
        fontFamily = Poppins
    )
}

@Composable
fun BulletPoint(text: String, modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(
                    textIndent = TextIndent(restLine = 14.sp) // keeps wrapped lines aligned
                )
            ) {
                append("•  ")
                append(text)
            }
        },
        modifier = modifier.padding(vertical = 2.dp),
        fontSize = 14.sp,
        fontFamily = Poppins
    )
}
