package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.features.home.presentation.screens.TopBar

@Composable
fun TnCScreen(navController: NavController){
    val date= "26/08/2025"
    Box(modifier= Modifier.fillMaxSize().padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp))
        {
            TopBar("Grievance Policy", onBackClick =  {navController.popBackStack()})
            LazyColumn(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)) {

                item {
                    Body(
                        "Effective Date: $date\n" +
                                "Welcome to EMIFY! By accessing or using our application and services, you agree to be bound by these Terms and Conditions. Please read them carefully before proceeding. If you do not agree with these terms, you must discontinue using EMIFY immediately."
                    )
                }
                item {
                    Heading("1. Definitions")
                }
                item {
                    val points = listOf(
                        "“App” refers to the EMIFY mobile or web application.",
                        "“User” refers to any individual or business entity accessing or using the app.",
                        "“We,” “Us,” or “Our” refers to EMIFY and its affiliates."
                    )
                    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                        points.forEach { point ->
                            BulletPoint(point)
                        }
                    }
                }

                item {
                    Heading("2. Use of Services")
                }

                item {
                    NumberedPoint(1, "Eligibility: You must be at least 18 years old and capable of entering into a binding agreement to use EMIFY.")
                    NumberedPoint(2, "Account Registration: You agree to provide accurate, complete, and up-to-date information during the registration process.")
                    NumberedPoint(3, "KYC Compliance: You must complete the Know Your Customer (KYC) process for accessing certain services, such as payment reconciliation and automated collections.")
                    NumberedPoint(4, "Prohibited Use:")

                    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                        val prohibitedPoints = listOf(
                            "Engaging in fraudulent, unlawful, or malicious activities.",
                            "Tampering with the app’s functionality or accessing unauthorized features.",
                            "Using EMIFY to process payments for prohibited goods or services."
                        )
                        prohibitedPoints.forEach { point ->
                            BulletPoint(point)
                        }
                    }
                }


                item {
                    Heading("3. Payment and Subscription")
                }

                item {
                    NumberedPoint(1, "Fees: Certain features may require a subscription or usage fee. Fee details will be communicated during the signup or payment process.")
                    NumberedPoint(2, "Refunds: Payments made are non-refundable unless explicitly stated.")
                    NumberedPoint(3, "Transaction Charges: EMIFY may apply nominal transaction charges for certain payment services. These charges will be disclosed transparently.")
                }

                item {
                    Heading("4. User Responsibilities")
                }

                item {
                    NumberedPoint(1, "Data Accuracy: You are responsible for ensuring the accuracy of all information provided in the app, including business and bank details.")
                    NumberedPoint(2, "Security: Keep your account credentials confidential. You are responsible for any activity conducted through your account.")
                    NumberedPoint(3, "Compliance: You must comply with all applicable laws, including payment regulations, in your jurisdiction.")
                }

                item {
                    Heading("5. Our Rights")
                }

                item {
                    NumberedPoint(1, "Service Modification: We may update, modify, or discontinue any feature or service without prior notice.")
                    NumberedPoint(2, "Account Suspension: We reserve the right to suspend or terminate accounts that violate these Terms or engage in prohibited activities.")
                    NumberedPoint(3, "Communication: We may send you notifications, updates, or promotional material related to EMIFY. You can opt out of promotional communications.")
                }

                item {
                    Heading("6. Limitation of Liability")
                }

                item {
                    NumberedPoint(1, "EMIFY is provided on an “as-is” and “as-available” basis. We do not guarantee uninterrupted access or error-free functionality.")
                    NumberedPoint(2, "To the fullest extent permitted by law, EMIFY will not be liable for any indirect, incidental, or consequential damages arising from the use of the app.")
                    NumberedPoint(3, "EMIFY is not responsible for the accuracy or reliability of third-party payment gateways or services integrated into the app.")
                }

                item {
                    Heading("7. Intellectual Property")
                }

                item {
                    NumberedPoint(1, "All content, logos, trademarks, and intellectual property within EMIFY are the exclusive property of EMIFY.")
                    NumberedPoint(2, "You may not copy, modify, distribute, or use any part of the app’s content without prior written consent.")
                }


                item {
                    Heading("8. Privacy Policy")
                }

                item {
                    Body("By using EMIFY, you agree to our Privacy Policy, which explains how we collect, use, and protect your data.")
                }

                item {
                    Heading("9. Termination")
                }

                item {
                    NumberedPoint(1, "You may terminate your account at any time by contacting our support team.")
                    NumberedPoint(2, "EMIFY reserves the right to suspend or terminate your access for breach of these Terms or misuse of the app.")
                }

                item {
                    Heading("10. Governing Law")
                }

                item {
                    Body("These Terms and Conditions are governed by the laws of [Insert Jurisdiction]. Any disputes arising from these Terms will be resolved in the courts of [Insert Location].")
                }

                item {
                    Heading("11. Amendments")
                }

                item {
                    Body("We may revise these Terms from time to time. Updates will be communicated through the app or email. Continued use of EMIFY constitutes acceptance of the updated terms.")
                }

                item {
                    Heading("12. Contact Us")
                }

                item {
                    Body("If you have questions, concerns, or feedback regarding these Terms, please contact us at:\nEmail: [Insert Email Address]\nPhone: [Insert Contact Number]\nAddress: [Insert Office Address]")
                }

                item {
                    Text(
                        text= buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    fontFamily = Poppins
                                )
                            ){
                                    append("By using ")
                                }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    fontFamily = Poppins
                                )
                            ) {
                                append("EMIFY")
                            }
                            withStyle(style = SpanStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins)) {
                                append(" , you acknowledge that you have read, understood, and agree to these Terms and Conditions. Thank you for choosing EMIFY as your trusted payment reconciliation partner!")
                            }
                        },
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

            }
        }
    }
}
