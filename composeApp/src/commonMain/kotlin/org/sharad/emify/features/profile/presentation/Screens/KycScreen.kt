package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.arrow_down
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.SharedComponents.DropDownTextField
import org.sharad.emify.core.ui.SharedComponents.TextButtonTextField
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.core.util.ChangeBackPress
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.profile.presentation.viewmodels.KycScreenViewModel

@Composable
fun KYCScreen(navController: NavController){

    val viewModel: KycScreenViewModel = koinViewModel()
    val entity by viewModel.entityType.collectAsStateWithLifecycle()
    val showAVField by viewModel.showAdhaarVerificationField.collectAsStateWithLifecycle()
    val showPVField by viewModel.showPanVerificationField.collectAsStateWithLifecycle()
    val aadhaarNo by viewModel.adhaarNumber.collectAsStateWithLifecycle()
    val panNo by viewModel.panNumber.collectAsStateWithLifecycle()

    val entityList= listOf("Personal", "Business")

    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center)
    {

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        )
        {
            TopBar("Complete your KYC", onBackClick = { navController.popBackStack() })
            LazyColumn(modifier= Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item{
                    DropDownTextField(
                        value = entity ?: "",
                        onValueChange = { viewModel.updateEntityType(it) },
                        label = "Select Entity Type",
                        selectionList = entityList,
                        placeholder = "Write your business name.",
                        icon = Res.drawable.arrow_down
                    )
                }

                entity?.let {

                    item{
                        AnimatedVisibility(it == "Personal") {
                            IndividualSelection(viewModel)
                        }

                        AnimatedVisibility(it == "Business") {
                            BusinessSelection(viewModel)
                        }
                    }

                }

            }
        }
        if (showAVField){
            AadhaarVerificationField(aadhaarNo = aadhaarNo, viewModel)
        }
        if (showPVField){
            PanVerificationField(panNo = panNo, viewModel)
        }
    }
}

@Composable
fun BusinessSelection(viewModel: KycScreenViewModel) {

    val adhaar by viewModel.adhaarNumber.collectAsStateWithLifecycle()
    val pan by viewModel.panNumber.collectAsStateWithLifecycle()
    val panSelfie by viewModel.selfie.collectAsStateWithLifecycle()
    val  docType by viewModel.docType.collectAsStateWithLifecycle()
    val gstCertificate by viewModel.gstCertificate.collectAsStateWithLifecycle()

    val docTypeList= listOf("GST Certificate","Udyam","Shop & Establishment Certificate","Certificate of Incorporation by MCA","Other Business Document")

    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        DropDownTextField(
            value = docType ?: "",
            onValueChange = { viewModel.updateDocType(it) },
            placeholder = "Select business proof document",
            label = "Select Document Type",
            selectionList = docTypeList,
            icon = Res.drawable.arrow_down
        )

        AnimatedVisibility(docType != null){
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)){

                TextButtonTextField(
                    value = gstCertificate?.toString() ?: "",
                    onValueChange = {  },
                    label = "Upload GST Certificate",
                    placeholder = "Upload Document",
                    textIcon = "Upload",
                    enable = false,
                    iconClick = {}
                )
                TextButtonTextField(
                    value = adhaar,
                    onValueChange = {
                        if (it.length <= 12 && it.all { char -> char.isDigit()})
                        viewModel.updateAdhaarNumber(it)
                                    },
                    label = "Verify Aadhaar Card",
                    placeholder = "Aadhaar Card Number",
                    textIcon = "Verify",
                    iconClick = {
                        viewModel.showAdhaarVerificationField(true)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textIconEnabled = adhaar.isNotEmpty() && adhaar.length == 12
                )

                TextButtonTextField(
                    value = pan,
                    onValueChange = {
                        val format= it.trim().toUpperCase(Locale.current)
                        viewModel.updatePanNumber(format)
                                    },
                    label = "Verify PAN Card",
                    placeholder = "PAN Card Number",
                    textIcon = "Verify",
                    iconClick = {
                        viewModel.showPanVerificationField(true)
                    },
                    textIconEnabled = pan.isNotEmpty(),
                )
                TextButtonTextField(
                    value = panSelfie?.toString() ?: "",
                    onValueChange = { viewModel.updateAdhaarNumber(it) },
                    label = "Selfie with PAN",
                    placeholder = "Upload Selfie with PAN",
                    textIcon = "Upload",
                    iconClick = {},
                    enable = false
                )
            }
        }
    }
}


@Composable
fun IndividualSelection(viewModel: KycScreenViewModel){

    val adhaar by viewModel.adhaarNumber.collectAsStateWithLifecycle()
    val pan by viewModel.panNumber.collectAsStateWithLifecycle()
    val panSelfie by viewModel.selfie.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextButtonTextField(
            value = adhaar,
            onValueChange = {
                if (it.length <= 12 && it.all { char -> char.isDigit()})
                    viewModel.updateAdhaarNumber(it)
            },
            label = "Verify Aadhaar Card",
            placeholder = "Aadhaar Card Number",
            textIcon = "Verify",
            iconClick = {
                viewModel.showAdhaarVerificationField(true)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textIconEnabled = adhaar.isNotEmpty() && adhaar.length == 12
            )
        TextButtonTextField(
            value = pan,
            onValueChange = {
                val format= it.trim().toUpperCase(Locale.current)
                viewModel.updatePanNumber(format)
            },
            label = "Verify PAN Card",
            placeholder = "PAN Card Number",
            textIcon = "Verify",
            iconClick = {
                viewModel.showPanVerificationField(true)
            },
            textIconEnabled = pan.isNotEmpty(),
        )
        TextButtonTextField(
            value = panSelfie?.toString() ?: "",
            onValueChange = {viewModel.updateAdhaarNumber(it)},
            label = "Selfie with PAN",
            placeholder = "Upload Selfie with PAN",
            textIcon = "Upload",
            iconClick = {},
            enable = false
        )
    }

}

@Composable
fun AadhaarVerificationField(aadhaarNo: String, viewModel: KycScreenViewModel){

    ChangeBackPress(true) {}

    var otp by remember { mutableStateOf("") }

    Box(modifier=Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f))
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {  },
        contentAlignment = Alignment.Center){

        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White, RoundedCornerShape(20.dp))
            ){

            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 28.dp, vertical = 36.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column( verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "Enter OTP shared at your number linked with your AADHAR CARD",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Poppins,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = formatWithSpaces(aadhaarNo),
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = appBlue,
                        fontFamily = Poppins,
                        textAlign = TextAlign.Center
                    )
                }

                OutlinedTextField(
                    value = otp,
                    onValueChange = { otp=it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = appBlue,
                        unfocusedBorderColor = appBlue,
                        focusedContainerColor = f7Gray,
                        unfocusedContainerColor = f7Gray,
                        cursorColor = appBlue,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    )
                )

                BottomButton(
                    text = "Verify OTP",
                    onClick = {
                    },
                    modifier = Modifier.padding()
                )

                TextButton(
                    onClick = {
                        viewModel.hideAdhaarVerificationField()
                    }
                ){
                    Text("Cancel",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = appBlue,
                        fontFamily = Poppins
                    )
                }
            }

        }

    }
}

@Composable
fun PanVerificationField(panNo: String, viewModel: KycScreenViewModel){

    ChangeBackPress(true) {}

    var otp by remember { mutableStateOf("") }

    Box(modifier=Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f))
        .clickable(
            indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {  },
        contentAlignment = Alignment.Center){

        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White, RoundedCornerShape(20.dp))
            ){

            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 28.dp, vertical = 36.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column( verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "Enter OTP shared at your number linked with your PAN CARD",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Poppins,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = panNo,
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = appBlue,
                        fontFamily = Poppins,
                        textAlign = TextAlign.Center
                    )
                }

                OutlinedTextField(
                    value = otp,
                    onValueChange = { otp=it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = appBlue,
                        unfocusedBorderColor = appBlue,
                        focusedContainerColor = f7Gray,
                        unfocusedContainerColor = f7Gray,
                        cursorColor = appBlue,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    )
                )

                BottomButton(
                    text = "Verify OTP",
                    onClick = {
                    },
                    modifier = Modifier.padding()
                )

                TextButton(
                    onClick = {
                        viewModel.hidePanVerificationField()
                    }
                ){
                    Text("Cancel",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = appBlue,
                        fontFamily = Poppins
                    )
                }


            }

        }

    }
}

fun formatWithSpaces(input: String): String {
    return input.chunked(4).joinToString(" ")
}