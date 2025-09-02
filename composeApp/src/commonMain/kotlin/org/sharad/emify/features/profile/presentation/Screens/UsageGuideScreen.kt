package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.features.home.presentation.screens.TopBar



@Composable
fun GuideScreen(navController: NavController) {
    val placeholderSupportEmail = "[Insert Support Email]"
    val placeholderSupportPhone = "[Insert Contact Number]"
    val placeholderEmailAddress = "[Insert Email Address]"
    val placeholderGooglePlay = "[Google Play Store]"
    val placeholderAppStore = "[Apple App Store]"
    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TopBar("Guide to use EMIFY App", onBackClick = { navController.popBackStack() })
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                item {
                    Body("Welcome to EMIFY! This step-by-step guide will help you navigate the app, set up your account, and use all the features effectively.")
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Heading("1. Getting Started")
                    }
                }

                item {
                    Column {
                        Heading("Step 1: Download the App")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Download the EMIFY app from the $placeholderGooglePlay or $placeholderAppStore.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Step 2: Create an Account")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Open the app and register using your mobile number or email.")
                            BulletPoint("Verify your account with the OTP sent to your mobile number or email.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Step 3: Complete KYC")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Go to the KYC Section and upload the required documents (e.g., PAN card, Aadhaar card, or business registration proof).")
                            BulletPoint("Once verified, you can access all the features of EMIFY.")
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Heading("2. Setting Up Your Profile")
                    }
                }

                item {
                    Column {
                        Heading("Step 1: Add Business Details")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Navigate to My Profile and fill in your business name, category, and contact information.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Step 2: Link Bank Accounts")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Go to the Bank Accounts section and add your bank details.")
                            BulletPoint("Set a primary account for transactions and reconciliation.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Step 3: Customize Your Preferences")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Enable the App Lock feature for added security in the settings.")
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Heading("3. Using EMIFY Features")
                    }
                }

                item {
                    Column {
                        Heading("Automated Payment Collection")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Navigate to Dashboard > Payment Requests.")
                            BulletPoint("Create a new payment request by entering the customer’s details and the payment amount.")
                            BulletPoint("EMIFY will send automated reminders for pending payments.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Reconciliation")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Check the Transactions section for detailed payment and reconciliation records.")
                            BulletPoint("Use filters to search by date, amount, or customer.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Refer & Earn")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Go to the Refer & Earn section and share your referral code with friends.")
                            BulletPoint("Earn rewards for every successful referral.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Reports and Analytics")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Visit the Reports section to view insights into your payments, collections, and reconciliation status.")
                            BulletPoint("Download reports for offline use.")
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Heading("4. Managing Customers")
                    }
                }

                item {
                    Column {
                        Heading("Step 1: Add Customer Details")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Go to Customers > Add New Customer.")
                            BulletPoint("Enter the customer’s name, email, phone number, and other details.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Step 2: Track Customer Payments")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("View pending, completed, or overdue payments for each customer in the Customer Profile section.")
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Heading("5. Security and Support")
                    }
                }

                item {
                    Column {
                        Heading("App Security")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Enable App Lock in the settings to protect your app with a PIN or biometric authentication.")
                        }
                    }
                }

                item {
                    Column {
                        Heading("Help & Support")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Access the Help & Support section for FAQs or to contact customer support.")
                            BulletPoint("Email us at $placeholderSupportEmail or call us at $placeholderSupportPhone for further assistance.")
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Heading("6. FAQs")
                    }
                }
                item {
                    Body("Check the FAQ Section in the app for quick answers to common questions.")
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Heading("7. Updates and Notifications")
                    }
                }
                item {
                    Body("Stay updated on new features and announcements via in-app notifications or emails.")
                }

                item {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Heading("Contact Us")
                    }
                }
                item {
                    Column {
                        Body("If you face any issues, feel free to reach out:")
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            BulletPoint("Email: $placeholderEmailAddress")
                            BulletPoint("Phone: $placeholderSupportPhone")
                        }
                    }
                }

                item {
                    Column(Modifier.padding(vertical = 12.dp)){
                        Body("Thank you for choosing EMIFY! We’re here to make payment reconciliation effortless.")
                    }                }
            }
        }
    }
}
