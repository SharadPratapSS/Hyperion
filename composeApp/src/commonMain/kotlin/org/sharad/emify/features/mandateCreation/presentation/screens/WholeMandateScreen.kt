package org.sharad.emify.features.mandateCreation.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.calendar_icon
import emify.composeapp.generated.resources.clock_icon
import emify.composeapp.generated.resources.paymentduration_icon
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.navigation.Routes_ConfirmWholeMandateScreen
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.core.util.formatMillisToSlashDate
import org.sharad.emify.core.util.formatMoney
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.mandateCreation.presentation.viewModels.AmountCollectionMethod
import org.sharad.emify.features.mandateCreation.presentation.viewModels.WholeBillingFrequency
import org.sharad.emify.features.mandateCreation.presentation.viewModels.WholeMandateScreenViewModel
import org.sharad.emify.features.mandateCreation.presentation.viewModels.toFrequency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WholeEmiMandateScreen(navController: NavController, name:String, phone:String, purpose:String, amount:String){

    val viewModel: WholeMandateScreenViewModel= koinViewModel()
    val showFrequencySelection by viewModel.showBFSelection.collectAsStateWithLifecycle()
    val showDurationSelector by viewModel.showDurationSelection.collectAsStateWithLifecycle()
    val showDatePicker by viewModel.showDateSelection.collectAsStateWithLifecycle()
    val nextEnabled by viewModel.nextEnabled.collectAsStateWithLifecycle()
    val installmentDate by viewModel.installmentDate.collectAsStateWithLifecycle()
    val installmentNumbers by viewModel.installmentNumbers.collectAsStateWithLifecycle()
    val installmentFrequency by viewModel.installmentFrequency.collectAsStateWithLifecycle()
    val selected by viewModel.installmentFrequency.collectAsStateWithLifecycle()
    val collectionType by viewModel.collectionType.collectAsStateWithLifecycle()
    val otpDate by viewModel.otpSelectionDate.collectAsStateWithLifecycle()
    val showOtpDateSelector by viewModel.showOtpDateSelector.collectAsStateWithLifecycle()



    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center)
    {

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        )
        {
            TopBar("Create Mandate", onBackClick = { navController.popBackStack() })
            Box(modifier= Modifier.weight(1f)) {
                Column(modifier=Modifier.fillMaxSize()
                    .verticalScroll(rememberScrollState())) {

                    Spacer(modifier = Modifier.height(16.dp))
                    MandateBanner(formatMoney(amount),purpose,name, navController=navController)

                    Spacer(modifier = Modifier.height(24.dp))

                    AmountCollection(selected=collectionType, onSelected = {it-> viewModel.setCollectionType(it)})

                    Spacer(modifier = Modifier.height(24.dp))

                    AnimatedVisibility(collectionType!=null) {
                        collectionType?.let{it
                            MethodOptions(
                                frequency = installmentFrequency,
                                duration = installmentNumbers,
                                date = installmentDate,
                                onBillingFrequencyClick = { viewModel.setShowBFSelection(true) },
                                onDurationClick = { viewModel.setShowDurationSelection(true) },
                                onDateClick = { viewModel.setShowDateSelection(true) },
                                selectedOption = it,
                                otpDate=otpDate,
                                showOtpDateSelector = {viewModel.setShowOtpDateSelector(true)}
                            )
                        }
                    }
                }
            }
            Box(modifier=Modifier.fillMaxWidth().padding(bottom = 12.dp)){
                BottomButton(
                    text = "Next",
                    onClick = {
                        collectionType?.let {
                            if (it== AmountCollectionMethod.ONE_TIME_PAYMENT){
                                otpDate?.let {date->
                                    navController.navigate(Routes_ConfirmWholeMandateScreen(
                                        name = name,
                                        phone = phone,
                                        purpose = purpose,
                                        amount = amount,
                                        otpCollectionDate = date,
                                        paymentType = AmountCollectionMethod.ONE_TIME_PAYMENT
                                    ))
                                }
                            }
                            else if (it== AmountCollectionMethod.INSTALLMENT){
                                installmentNumbers?.let {
                                    installmentDate?.let { it1 ->
                                        installmentFrequency?.let { frequency1 ->
                                            navController.navigate(Routes_ConfirmWholeMandateScreen(
                                                name = name,
                                                phone = phone,
                                                purpose = purpose,
                                                amount = amount,
                                                paymentType = AmountCollectionMethod.INSTALLMENT,
                                                installmentFrequency = frequency1,
                                                installmentDate = it1,
                                                installmentsNumber = it
                                            ))
                                        }
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier,
                    enabled = nextEnabled
                )
            }
        }
        if (showFrequencySelection){
            WholeFrequencySelectionBottomDialog(
                show = showFrequencySelection,
                selected = selected,
                onDismiss = { viewModel.setShowBFSelection(false) },
                onSelect = { viewModel.setBillFrequency(it) }
            )
        }

        if (showDurationSelector){
            InstallmentNoSelectionBox(
                show = showDurationSelector,
                initialDuration = installmentNumbers,
                onDismiss = { viewModel.setShowDurationSelection(false) },
                onConfirm = { newDuration -> viewModel.setDuration(newDuration) },
                amount=amount,
                frequency=installmentFrequency
            )
        }

        if (showDatePicker){
            DatePickerSelector(
                show = showDatePicker,
                selectedDate = installmentDate,
                onDismiss = { viewModel.setShowDateSelection(false) },
                onDateSelected = { millis -> viewModel.setDate(millis) }
            )
        }

        if (showOtpDateSelector){
            DatePickerSelector(
                show = showOtpDateSelector,
                selectedDate = otpDate,
                onDismiss = { viewModel.setShowOtpDateSelector(false) },
                onDateSelected = { millis -> viewModel.setOtpSelectionDate(millis) }
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WholeFrequencySelectionBottomDialog(
    show: Boolean,
    selected: WholeBillingFrequency?,
    onDismiss: () -> Unit,
    onSelect: (WholeBillingFrequency) -> Unit
) {
    if (!show) return

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = Color.White,
        contentColor = f7Gray
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                "Select Installment Frequency",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Poppins,
                color = appBlue
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WholeBillingFrequency.entries.forEachIndexed { index, item ->
                    RadioButton(
                        selected = selected == item,
                        label = item.label,
                        onClick = {
                            scope.launch {
                                onSelect(item)
                                onDismiss()
                            }
                        }
                    )
                    if (index != WholeBillingFrequency.entries.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                            color = f7Gray,
                            thickness = 2.dp
                        )
                    } else {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstallmentNoSelectionBox(
    show: Boolean,
    initialDuration: Int?,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit,
    amount: String,
    frequency: WholeBillingFrequency?
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = Color.White,
        contentColor = f7Gray
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                "Select Installment Frequency",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Poppins,
                color = appBlue
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(12) { index->
                    Row(modifier=Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween){
                        RadioButton(
                            selected = initialDuration == index + 1,
                            label = "${index + 1} installments",
                            onClick = {
                                scope.launch {
                                    onConfirm(index + 1)
                                    onDismiss()
                                }
                            }
                        )

                        if (index<2){
                            Text(
                                text = "₹ "+ formatMoney((amount.toInt()/(index+1)).toString())+ "/per " + (frequency?.toFrequency()
                                    ?: "installment"),
                                fontSize = 12.sp,
                                color = Color(0xff181818),
                                fontFamily = Poppins,
                                style = TextStyle(letterSpacing = (-(0.05)).em)
                            )
                        }else{
                            Text(
                                text = "${index+1} installments",
                                fontSize = 12.sp,
                                color = Color(0xff181818),
                                fontFamily = Poppins,
                                style = TextStyle(letterSpacing = (-(0.05)).em)
                            )
                        }
                    }
                    if (index != 11) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                            color = f7Gray,
                            thickness = 2.dp
                        )
                    } else {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MethodOptions(
    frequency: WholeBillingFrequency?,
    duration: Int?,
    date: Long?,
    onBillingFrequencyClick: () -> Unit,
    onDurationClick: () -> Unit,
    onDateClick: () -> Unit,
    selectedOption: AmountCollectionMethod?,
    otpDate: Long?,
    showOtpDateSelector: () -> Unit
){

    selectedOption?.let {
            when(it){
                AmountCollectionMethod.ONE_TIME_PAYMENT -> {
                    PaymentScheduleOneTime(
                        date = otpDate,
                        onDateClick = { showOtpDateSelector ()}
                    )
                }
                AmountCollectionMethod.INSTALLMENT -> {
                    PaymentScheduleWholeInstallments(
                        frequency = frequency,
                        installmentNo = duration,
                        date = date,
                        onBillingFrequencyClick = { onBillingFrequencyClick() },
                        onInstallmentNoClick = {onDurationClick()},
                        onDateClick = { onDateClick ()}
                    )
                }
            }
    }
}

@Composable
fun PaymentScheduleOneTime(date: Long?, onDateClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Schedule of Payment",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Poppins,
            maxLines = 1
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PaymentScheduleItem(
                icon = Res.drawable.calendar_icon,
                placeHolder = "Select Collection Date",
                value = date?.let { formatMillisToSlashDate(it) },
                onClick = onDateClick
            )
        }
    }
}

@Composable
fun PaymentScheduleWholeInstallments(
    frequency: WholeBillingFrequency?,
    installmentNo: Int?,
    date: Long?,
    onBillingFrequencyClick: () -> Unit,
    onInstallmentNoClick: () -> Unit,
    onDateClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Schedule of Payment",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Poppins,
            maxLines = 1
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PaymentScheduleItem(
                icon = Res.drawable.clock_icon,
                placeHolder = "Installment Frequency",
                value = frequency?.label,
                onClick = onBillingFrequencyClick
            )
            PaymentScheduleItem(
                icon = Res.drawable.paymentduration_icon,
                placeHolder = "Number of Installments",
                value = installmentNo?.let {
                    "$it Installments"
                },
                onClick = onInstallmentNoClick
            )
            PaymentScheduleItem(
                icon = Res.drawable.calendar_icon,
                placeHolder = "Start Date",
                value = date?.let { formatMillisToSlashDate(it) },
                onClick = onDateClick
            )
        }
    }
}

@Composable
fun AmountCollection(selected: AmountCollectionMethod?, onSelected: (AmountCollectionMethod) -> Unit) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Schedule of Payment",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Poppins,
            maxLines = 1
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AmountCollectionMethod.entries.forEachIndexed { index, method ->
                MethodSelectionLabel(method, method==selected, onSelected = { onSelected(method) })
            }
        }
    }

}

@Composable
fun MethodSelectionLabel(
    method: AmountCollectionMethod,
    selected: Boolean,
    onSelected: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxWidth()
            .height(52.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp))
            .clickable(onClick = { onSelected() }),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier.size(22.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = if (selected) appBlue else f7Gray,
                        shape = CircleShape
                    )
                    .clickable { onSelected() }
            ) {
                if (selected) {
                    Box(
                        modifier = Modifier.fillMaxSize().clip(CircleShape).padding(4.dp)
                            .background(appBlue, CircleShape)
                    )
                }
            }

            Text(
                text = method.label.capitalize(Locale.current),
                fontSize = 16.sp,
                fontFamily = Poppins,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}