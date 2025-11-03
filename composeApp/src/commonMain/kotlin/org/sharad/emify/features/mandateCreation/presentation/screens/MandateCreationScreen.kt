package org.sharad.emify.features.mandateCreation.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.arrow_down
import emify.composeapp.generated.resources.upi_footer
import emify.composeapp.generated.resources.user_icon
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.navigation.Routes_SubscriptionMandateScreen
import org.sharad.emify.core.navigation.Routes_WholeEmiMandateScreen
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.SharedComponents.GenericDropDownField
import org.sharad.emify.core.ui.SharedComponents.IconTextField
import org.sharad.emify.core.ui.SharedComponents.NormalTextField
import org.sharad.emify.core.ui.SharedComponents.PhoneNumberTextField
import org.sharad.emify.core.ui.SharedComponents.PreTextField
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.core.util.MandateOption
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.mandateCreation.presentation.viewModels.MandateCreationViewModel

@Composable
fun MandateCreationScreen(navController: NavController){

    val viewModel: MandateCreationViewModel = koinViewModel()
    val mandateList = viewModel.mandateOptions
    val selectedOption by viewModel.selectedOption.collectAsStateWithLifecycle()
    val nextEnabled by viewModel.nextEnabled.collectAsStateWithLifecycle()

    Box(modifier= Modifier.fillMaxSize().background(f7Gray).imePadding(),
        contentAlignment = Alignment.Center)
    {

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        )
        {
            TopBar("Create Mandate", onBackClick = { navController.popBackStack() })
            Column(modifier= Modifier.weight(1f)) {
                LazyColumn(modifier=Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(20.dp)) {

                    item{
                        GenericDropDownField(
                            value = selectedOption,
                            onValueChange = { viewModel.updateSelectedOption(it) },
                            label = "Mandate Type",
                            selectionList = mandateList,
                            placeholder = "Select your Mandate type",
                            icon = Res.drawable.arrow_down,
                            labelMapper = { it?.label ?: "" }
                        )
                    }

                    selectedOption?.let {
                        if (selectedOption== MandateOption.SUBSCRIPTION){
                            item{ SubscriptionMandateForm(viewModel) }
                        }
                        else if (selectedOption== MandateOption.WHOLE_AMOUNT){
                            item{ WholeAmountMandateForm(viewModel) }
                        }
                    }

                }
                Spacer(Modifier.height(8.dp))
                Image(painter = painterResource(Res.drawable.upi_footer), contentDescription = "footer", modifier = Modifier.padding(bottom = 12.dp))
                Spacer(Modifier.height(8.dp))
                BottomButton(
                    text = "Next",
                    onClick = {
                       if (selectedOption== MandateOption.SUBSCRIPTION){
                           navController.navigate(Routes_SubscriptionMandateScreen(
                               name = viewModel.customerName.value,
                               phone = viewModel.customerPhone.value,
                               purpose = viewModel.purposeOfCollection.value,
                               amount = viewModel.collectionAmount.value
                           ))
                       }else if (selectedOption== MandateOption.WHOLE_AMOUNT){
                           navController.navigate(Routes_WholeEmiMandateScreen(
                               name = viewModel.customerName.value,
                               phone = viewModel.customerPhone.value,
                               purpose = viewModel.purposeOfCollection.value,
                               amount = viewModel.collectionAmount.value
                           ))
                       }
                    },
                    modifier = Modifier,
                    enabled = nextEnabled
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun WholeAmountMandateForm(viewModel: MandateCreationViewModel) {

    val collectionAmount by viewModel.collectionAmount.collectAsStateWithLifecycle()
    val customerName by viewModel.customerName.collectAsStateWithLifecycle()
    val customerPhone by viewModel.customerPhone.collectAsStateWithLifecycle()
    val purposeOfCollection by viewModel.purposeOfCollection.collectAsStateWithLifecycle()
    val showAmountError by viewModel.showAmountError.collectAsStateWithLifecycle()
    val showNumberError by viewModel.showNumberError.collectAsStateWithLifecycle()
    val  error = when (val amount = collectionAmount.toIntOrNull()) {
        null -> "Invalid Amount"
        in 0..999 -> "amount can’t be less than ₹1000"
        else -> "Invalid Amount"
    }

    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        PreTextField(
            value = collectionAmount,
            onValueChange = { viewModel.updateCollectionAmount(it) },
            placeholder = "Enter the total amount to be collected",
            label = "Amount to Collect",
            preText = "₹",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone
            ),
            showError = showAmountError,
            error = error
        )

        Column(modifier= Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)){
            IconTextField(
                value = customerName,
                onValueChange = { viewModel.updateCustomerName(it) },
                placeholder = "Customer Name",
                icon = Res.drawable.user_icon,
                label = "Customer Information",
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )

            PhoneNumberTextField(
                value = customerPhone,
                onValueChange = { viewModel.updateCustomerPhone(it) },
                placeholder = "Phone Number",
                showError = showNumberError
            )
        }

        LargeTextField(
            value = purposeOfCollection,
            onValueChange = {
                viewModel.updatePurposeOfCollection(it)
            },
            placeholder = "Describe the reason or details for this collection (e.g., Invoice #, Bill #).",
            label = "Purpose of Collection"
        )

    }
}

@Composable
fun SubscriptionMandateForm(viewModel: MandateCreationViewModel) {

    val collectionAmount by viewModel.collectionAmount.collectAsStateWithLifecycle()
    val customerName by viewModel.customerName.collectAsStateWithLifecycle()
    val customerPhone by viewModel.customerPhone.collectAsStateWithLifecycle()
    val purposeOfCollection by viewModel.purposeOfCollection.collectAsStateWithLifecycle()
    val showAmountError by viewModel.showAmountError.collectAsStateWithLifecycle()
    val showNumberError by viewModel.showNumberError.collectAsStateWithLifecycle()

   val  error = when (val amount = collectionAmount.toIntOrNull()) {
        null -> "Invalid Amount"
        in 0..999 -> "amount can’t be less than ₹1000"
        else -> "Invalid Amount"
    }

    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        PreTextField(
            value = collectionAmount,
            onValueChange = { viewModel.updateCollectionAmount(it) },
            placeholder = "Enter the total amount to be collected",
            label = "Amount to Collect",
            preText = "₹",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone
            ),
            showError = showAmountError,
            error = error
        )

        Column(modifier= Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)){
            IconTextField(
                value = customerName,
                onValueChange = { viewModel.updateCustomerName(it) },
                placeholder = "Customer Name",
                icon = Res.drawable.user_icon,
                label = "Customer Information"
            )

            PhoneNumberTextField(
                value = customerPhone,
                onValueChange = { viewModel.updateCustomerPhone(it) },
                placeholder = "Phone Number",
                showError = showNumberError
            )
        }

        LargeTextField(
            value = purposeOfCollection,
            onValueChange = {
                viewModel.updatePurposeOfCollection(it)
            },
            placeholder = "Describe the reason or details for this collection (e.g., Invoice #, Bill #).",
            label = "Purpose of Collection"
        )

    }
}

@Composable
fun LargeTextField(value: String, onValueChange: (String) -> Unit,
             placeholder: String,
             label: String?=null,
             enable:Boolean=true,
){
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        label?.let{it
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth().padding(start = 2.dp),
                fontFamily = Poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        BasicTextField(
            enabled = enable,
            value = value,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                color = Color.Black
            ),
            modifier = Modifier
//                    .padding(top = 4.dp, bottom = 12.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(12.dp),
                    spotColor = Color(0xFFDEE2F6BF)
                )
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White, RoundedCornerShape(12.dp)),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                fontFamily = Poppins,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFFB5B5B5)
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
    }
}