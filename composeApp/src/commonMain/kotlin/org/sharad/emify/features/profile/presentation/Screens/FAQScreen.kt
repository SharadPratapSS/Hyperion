package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.features.home.presentation.screens.TopBar

@Composable
fun FAQScreen(navController: NavController) {

    val supportEmail = "[Insert Support Email]"
    val supportPhone = "[Insert Contact Number]"
    val appStoreLink = "[Insert App Store Link]"
    val googlePlayLink = "[Insert Google Play Link]"

    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TopBar("FAQs", onBackClick = { navController.popBackStack() })

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item {
                    Heading("Frequently Asked Questions (FAQ)")
                }
                item {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    fontFamily = Poppins
                                )
                            ) {
                                append("Welcome to the ")
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
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    fontFamily = Poppins
                                )
                            ) {
                                append(
                                    " FAQ page! \nHere, you'll find answers to common questions about our services, features, and app functionality. If you don’t find your question here, feel free to contact our support team."
                                )
                            }
                        },
                    )
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) { Heading("General Questions") }
                }

                item {
                    Column {
                        Heading("Q1: What is EMIFY?")
                        Body("EMIFY is a payment reconciliation app designed to help businesses automate payment collection and streamline financial workflows.")
                    }
                }

                item {
                    Column {
                        Heading("Q2: Who can use EMIFY?")
                        Body("EMIFY is ideal for vendors, small business owners, freelancers, and anyone looking to simplify payment management.")
                    }
                }

                item {
                    Column {
                        Heading("Q3: Is EMIFY free to use?")
                        Body("EMIFY offers both free and premium plans. Some features may require a subscription fee or transaction-based charges.")
                    }
                }

                // Section Heading
                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) { Heading("Account Setup") }
                }

                item {
                    Column {
                        Heading("Q4: How do I create an account on EMIFY?")
                        Column(modifier = Modifier.padding(vertical = 8.dp)) {
                            BulletPoint("Download the EMIFY app from $appStoreLink / $googlePlayLink.")
                            BulletPoint("Register using your mobile number or email address.")
                            BulletPoint("Complete the KYC process to access all features.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Q5: What documents are needed for KYC verification?")
                        Body("You’ll need a government-issued ID (e.g., PAN card, Aadhaar card) and bank details for verification.")
                    }
                }

                item {
                    Column {
                        Heading("Q6: Can I update my business details later?")
                        Body("Yes, you can edit your business name and other details in the app under the “Profile” section.")
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) { Heading("Payments & Reconciliation") }
                }

                item {
                    Column {
                        Heading("Q7: How does EMIFY automate payment collections?")
                        Body("EMIFY integrates with your preferred payment methods and sends automated reminders to customers for pending payments, ensuring faster collections.")
                    }
                }

                item {
                    Column {
                        Heading("Q8: Are there any transaction fees?")
                        Body("While basic features are free, some payment transactions may include minimal charges based on your plan.")
                    }
                }

                item {
                    Column {
                        Heading("Q9: How can I track my payment history?")
                        Body("You can view detailed payment and reconciliation reports in the “Transactions” section of the app.")
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) { Heading("Security") }
                }

                item {
                    Column {
                        Heading("Q10: Is my data secure with EMIFY?")
                        Body("Absolutely! We use advanced encryption to protect your data and follow industry-leading security standards.")
                    }
                }

                item {
                    Column {
                        Heading("Q11: How can I secure my EMIFY app?")
                        Body("You can enable the “App Lock” feature in the settings to add an extra layer of security.")
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) { Heading("Features") }
                }

                item {
                    Column {
                        Heading("Q12: What is the Refer & Earn program?")
                        Body("Invite your friends to join EMIFY, and earn rewards for each successful referral. You can find this feature under the “Refer & Earn” section.")
                    }
                }

                item {
                    Column {
                        Heading("Q13: Can I manage multiple bank accounts?")
                        Body("Yes, EMIFY allows you to add and manage multiple bank accounts. You can also set a primary account for transactions.")
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) { Heading("Troubleshooting") }
                }

                item {
                    Column {
                        Heading("Q14: I’m unable to complete KYC. What should I do?")
                        Body("Ensure your documents are clear and match the required formats. If the issue persists, contact our support team.")
                    }
                }

                item {
                    Column {
                        Heading("Q15: What should I do if my payment fails?")
                        Body("If a payment fails, check your internet connection or bank details. You can also contact support for assistance.")
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) { Heading("Support") }
                }

                item {
                    Column {
                        Heading("Q16: How can I contact EMIFY support?")
                        Body("You can reach us through the “Help & Support” section in the app, or contact us via email at $supportEmail or phone at $supportPhone.")
                    }
                }

                item {
                    Column {
                        Heading("Q17: What are your support hours?")
                        Body("Our support team is available Monday to Friday, 9:00 AM to 6:00 PM.")
                    }
                }

                item {
                    Column {
                        Heading("Still have questions?")
                        Body("Contact us at $supportEmail or visit the “Help & Support” section in the app. We’re here to help!")
                    }
                }
            }
        }
    }
}
