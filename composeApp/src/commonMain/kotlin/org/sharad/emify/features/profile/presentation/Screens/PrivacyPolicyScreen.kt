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
fun PrivacyPolicyScreen(navController: NavController){
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
                    Body("Effective Date: $date\n" +
                            "At EMIFY, your privacy is our top priority. This Privacy Policy outlines how we collect, use, protect, and disclose your personal information when you use our app or services. By accessing or using EMIFY, you agree to the terms of this Privacy Polic") }

                item {
                    Heading("1. Information We Collect")
                }
                item {
                    Column {
                        Body("1.1 Personal Information")
                        BulletPoint(
                            "Name, email address, phone number, business details.",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        BulletPoint(
                            "KYC details for verification purposes.",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        BulletPoint(
                            "Bank account details for payment processing.",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Body("1.2 Non-Personal Information")
                        BulletPoint(
                            "Device information (e.g., model, operating system).",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        BulletPoint(
                            "IP address and usage data.",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        BulletPoint(
                            "Cookies and analytics data for app performance improvement.",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }

                }

                item {
                    Heading("2. How We Use Your Information")
                }

                item {
                    val points = listOf(
                        "Facilitate automated payment collection and reconciliation.",
                        "Process payments securely and ensure compliance with legal requirements.",
                        "Provide customer support and improve user experience.",
                        "Send notifications, updates, or promotional offers (with your consent).",
                        "Prevent fraudulent activities and maintain security."
                    )

                    Column{
                        Body("We use your information to:")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            points.forEach { point ->
                                BulletPoint(point)
                            }
                        }
                    }
                }

                item {
                    Heading("3. Sharing Your Information")
                }

                item {
                    val points = listOf(
                        "With service providers: For payment processing, KYC verification, and technical support.",
                        "With regulatory authorities: To comply with legal obligations.",
                        "With your consent: For any other purpose explicitly approved by you."
                    )

                    Column{
                        Body("We may share your data only in the following circumstances:")
                        Column(modifier = Modifier.padding(8.dp)) {
                            points.forEachIndexed { index, point ->
                                BulletPoint( point)
                            }
                        }
                        Body("We do not sell or rent your personal information to third parties.")
                    }
                }


                item {
                    Heading("4. Data Security")
                }

                item {
                    val points = listOf(
                        "End-to-end encryption for sensitive information.",
                        "Regular security audits and compliance with industry standards.",
                        "Access controls to prevent unauthorized access."
                    )

                    Column{
                        Body("We implement robust security measures to protect your data, including:")
                        Column(modifier = Modifier.padding(8.dp)) {
                            points.forEachIndexed { index, point ->
                                BulletPoint (point)
                            }
                        }
                    }
                }

                item {
                    Heading("5. Data Retention")
                }

                item {
                    Body("We retain your information only as long as necessary to fulfill the purposes outlined in this policy or as required by law. Once the retention period ends, your data will be securely deleted or anonymized.")
                }

                item {
                    Heading("6. Your Rights")
                }


                item {
                    val points = listOf(
                        "Access, update, or delete your personal information.",
                        "Withdraw consent for data processing.",
                        "Opt-out of promotional communications.",
                        "Request a copy of your data in a portable format."
                    )

                    Column{
                        Body("As a user, you have the following rights:")
                        Column(modifier = Modifier.padding(8.dp)) {
                            points.forEachIndexed { index, point ->
                                BulletPoint( point)
                            }
                        }
                        Body("For assistance with any of these rights, contact us at [Insert Contact Email].")
                    }
                }

                item {
                    Heading("7. Third-Party Links")
                }

                item {
                    Body("EMIFY may contain links to third-party websites or services. We are not responsible for the privacy practices of these external platforms. Please review their privacy policies before sharing your information.")
                }

                item {
                    Heading("8. Updates to This Policy")
                }

                item {
                    Body("We may update this Privacy Policy from time to time. Any changes will be communicated via app notifications or email. Please review the updated policy to stay informed.")
                }

                item {
                    Heading("9. Contact Us")
                }

                item {
                    Body("If you have any questions, concerns, or feedback regarding this Privacy Policy, please contact us at:\n" +
                            "Email: [Insert Email Address]\nPhone: [Insert Contact Number]\nAddress: [Insert Office Address]")
                }

                item {
                    Text(
                        text= buildAnnotatedString {

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
                                append(" is committed to safeguarding your privacy and ensuring a secure experience. Thank you for trusting us with your payment management needs")
                            }
                        },
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

            }
        }
    }
}
