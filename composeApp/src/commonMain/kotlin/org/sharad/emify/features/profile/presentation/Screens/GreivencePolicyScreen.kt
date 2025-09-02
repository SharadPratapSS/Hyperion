package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.features.home.presentation.screens.TopBar

@Composable
fun GreivancePolicyScreen(navController: NavController){
    val date= "26/08/2025"
    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp))
        {
            TopBar("Grievance Policy", onBackClick =  {navController.popBackStack()})
            LazyColumn(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)) {

                item {
                    Body("Effective Date: $date\n" +
                            "At EMIFY, we are committed to providing a seamless and satisfactory experience to our users. Your feedback and concerns are invaluable to us. This Grievance Redressal Policy outlines the procedure for addressing any issues or complaints regarding our services.")
                }

                item {
                    Heading("1. Objective")
                }

                item {
                    val points = listOf(
                        "Ensure timely and effective resolution of grievances.",
                        "Enhance user satisfaction by addressing concerns in a transparent manner.",
                        "Provide a structured process for users to escalate unresolved issues."
                    )
                    Column{
                        Body("This policy aims to:")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            points.forEach { point ->
                                BulletPoint(point)
                            }
                        }
                    }
                }

                item {
                    Heading("2. Scope")
                }

                item {
                    val points = listOf(
                        "App functionality and service delivery.",
                        "Payment transactions and reconciliation issues.",
                        "Data privacy and security concerns.",
                        "Any other matter related to EMIFY’s services."
                    )

                    Column{
                        Body("This policy applies to all users of EMIFY, including individuals and businesses, and covers grievances related to:")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            points.forEach { point ->
                                BulletPoint(point)
                            }
                        }
                    }
                }

                item {
                    Heading("3. Grievance Reporting Channels")
                }

                item {
                    val points = listOf(
                        "In-App Support: Access the “Help & Support” section in the EMIFY app.",
                        "Email: Write to us at [Insert Support Email Address].",
                        "Phone: Call our customer support helpline at [Insert Contact Number].",
                        "Mail: Address your written grievances to: [Insert Physical Address]"
                    )

                    Column{
                        Body("We encourage users to report their grievances through any of the following channels:")
                        Column(modifier = Modifier.padding(8.dp)) {
                            points.forEachIndexed { index, point ->
                                NumberedPoint(index + 1, point)
                            }
                        }
                    }
                }


                item {
                    Heading("4. Grievance Handling Process")
                }

                item {
                    Column {
                        Body("4.1 Acknowledgment")
                        BulletPoint(
                            "All grievances will be acknowledged within 24 hours of receipt through email or SMS.",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Body("4.2 Resolution Timeline")
                        BulletPoint(
                            "We strive to resolve grievances within 7 business days from the date of acknowledgment. In case additional time is required, users will be informed about the delay and provided with regular updates.",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Body("4.3 Escalation")
                        BulletPoint(
                            "If a grievance is not resolved to your satisfaction, you can escalate the issue to the Grievance Officer. Grievance Officer Details: Name: [Insert Grievance Officer Name] Email: [Insert Email Address] Phone: [Insert Contact Number] Address: [Insert Office Address]",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Body("4.4 Final Resolution")
                        BulletPoint(
                            "If the matter remains unresolved even after escalation, you may approach the appropriate legal or regulatory authorities as per the applicable laws.",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }

                }

                item {
                    Heading("5. Grievance Officer Details")
                }
                item {
                    val points = listOf(
                        "Name: [Insert Grievance Officer Name]",
                        "Email: [Insert Email Address]",
                        "Phone: [Insert Contact Number]",
                        "Working Hours: Monday to Friday, 9:00 AM to 6:00 PM"
                    )

                    Column{
                        Body("The Grievance Officer is responsible for ensuring timely resolution of complaints and compliance with this policy.")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            points.forEachIndexed { index, point ->
                                NumberedPoint(index + 1, point)
                            }
                        }
                    }
                }



                item {
                    Heading("6. User Responsibilities")
                }

                item {
                    val points = listOf(
                        "Provide accurate details of the grievance, including any relevant screenshots or transaction IDs.",
                        "Refrain from abusive language or inappropriate behavior during grievance submission."
                    )

                    Column{
                        Body("To ensure effective resolution, users are requested to:")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            points.forEachIndexed { index, point ->
                                NumberedPoint(index + 1, point)
                            }
                        }
                    }
                }
                item {
                    Heading("7. Policy Updates")
                }

                item {
                    Body("EMIFY reserves the right to amend or update this policy from time to time. Any changes will be communicated through the app or our official website.")
                }

                item {
                    Heading("8. Contact Us")
                }

                item {
                    Body("For any questions or clarifications regarding this policy, please contact us at: \nEmail: [Insert Support Email Address]\nPhone: [Insert Contact Number]")
                }

                item {
                    Text(
                        text= buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                fontSize = 14.sp,
                                fontFamily = Poppins)) {
                                append("At ")
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
                                append(", your trust and satisfaction are our top priorities. We are dedicated to addressing your grievances and continuously improving our services. Thank you for choosing EMIFY!")
                            }
                        },
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

            }
        }
    }
}

@Composable
fun NumberedPoint(number: Int, text: String) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(
                    textIndent = TextIndent(restLine = 18.sp)
                )
            ) {
                append("$number. ")
                append(text)
            }
        },
        fontSize = 14.sp,
        fontFamily = Poppins,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
