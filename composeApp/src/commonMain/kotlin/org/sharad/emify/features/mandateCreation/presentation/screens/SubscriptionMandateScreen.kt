package org.sharad.emify.features.mandateCreation.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.calendar_icon
import emify.composeapp.generated.resources.clock_icon
import emify.composeapp.generated.resources.edit_icon
import emify.composeapp.generated.resources.paymentduration_icon
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.navigation.Routes_ConfirmSubscriptionMandateScreen
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.core.util.formatDateParts
import org.sharad.emify.core.util.formatMillisDateParts
import org.sharad.emify.core.util.formatMillisToSlashDate
import org.sharad.emify.core.util.getTodayDate
import org.sharad.emify.core.util.getTodayMillis
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.mandateCreation.presentation.viewModels.BillingFrequency
import org.sharad.emify.features.mandateCreation.presentation.viewModels.SubscriptionMandateScreenViewModel
import org.sharad.emify.features.mandateCreation.presentation.viewModels.toFrequency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionMandateScreen(navController: NavController, name:String, phone:String, purpose:String, amount:String){

    val viewModel: SubscriptionMandateScreenViewModel= koinViewModel()
    val showFrequencySelection by viewModel.showBFSelection.collectAsStateWithLifecycle()
    val showDurationSelector by viewModel.showDurationSelection.collectAsStateWithLifecycle()
    val showDatePicker by viewModel.showDateSelection.collectAsStateWithLifecycle()
    val nextEnabled by viewModel.nextEnabled.collectAsStateWithLifecycle()
    val date by viewModel.date.collectAsStateWithLifecycle()
    val duration by viewModel.duration.collectAsStateWithLifecycle()
    val frequency by viewModel.billFrequency.collectAsStateWithLifecycle()
    val selected by viewModel.billFrequency.collectAsStateWithLifecycle()
    val selectedDate by viewModel.date.collectAsStateWithLifecycle()



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
                    MandateBanner(amount,purpose,name, navController=navController)

                    Spacer(modifier = Modifier.height(24.dp))
                    PaymentSchedule(
                        frequency = frequency,
                        duration = duration,
                        date = date,
                        onBillingFrequencyClick = { viewModel.setShowBFSelection(true) },
                        onDurationClick = { viewModel.setShowDurationSelection(true) },
                        onDateClick = { viewModel.setShowDateSelection(true) }
                    )

                }
            }
            Box(modifier=Modifier.fillMaxWidth().padding(bottom = 12.dp)){
                BottomButton(
                    text = "Next",
                    onClick = {
                        duration?.let {
                            date?.let { it1 ->
                                frequency?.let { frequency1 ->
                                    navController.navigate(Routes_ConfirmSubscriptionMandateScreen(
                                        name = name,
                                        phone = phone,
                                        purpose = purpose,
                                        amount = amount,
                                        duration = it,
                                        date = it1,
                                        frequency = frequency1
                                    ))
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
            FrequencySelectionBottomDialog(
                show = showFrequencySelection,
                selected = selected,
                onDismiss = { viewModel.setShowBFSelection(false) },
                onSelect = { viewModel.setBillFrequency(it) }
            )
        }

        if (showDurationSelector){
            DurationSelectionBottomDialog(
                show = showDurationSelector,
                initialDuration = duration,
                frequency = frequency,
                onDismiss = { viewModel.setShowDurationSelection(false) },
                onConfirm = { newDuration -> viewModel.setDuration(newDuration) }
            )
        }

        if (showDatePicker){
            DatePickerSelector(
                show = showDatePicker,
                selectedDate = selectedDate,
                onDismiss = { viewModel.setShowDateSelection(false) },
                onDateSelected = { millis -> viewModel.setDate(millis) }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerSelector(
    show: Boolean,
    selectedDate: Long?,
    onDismiss: () -> Unit,
    onDateSelected: (Long?) -> Unit
) {
    if (!show) return

    val state = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate,
        selectableDates = object : SelectableDates{
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= getTodayMillis()
            }
        }
    )

    val (year, formattedDate) = remember(selectedDate) {
        if (selectedDate == null) {
            val today = getTodayDate()
            formatDateParts(today)
        } else {
            formatMillisDateParts(selectedDate) ?: run {
                val today = getTodayDate()
                formatDateParts(today)
            }
        }
    }

    DatePickerDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    "Cancel",
                    color = appBlue,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins,
                    fontSize = 16.sp
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(state.selectedDateMillis)
                onDismiss()
            }) {
                Text(
                    "OK",
                    color = appBlue,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        },
        modifier = Modifier.scale(0.9f)
    ) {
        DatePicker(
            state = state,
            title = null,
            headline = {
                Box(Modifier.fillMaxWidth().background(appBlue)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 20.dp)
                    ) {
                        Text(
                            text = year,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Poppins,
                            color = Color.White
                        )
                        Text(
                            text = formattedDate,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Poppins,
                            color = Color.White
                        )
                    }
                }
            },
            showModeToggle = false
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DurationSelectionBottomDialog(
    show: Boolean,
    initialDuration: Int?,                // hoisted
    frequency: BillingFrequency?,         // hoisted
    onDismiss: () -> Unit,                // hoisted
    onConfirm: (Int) -> Unit              // hoisted
) {

    var selectedDuration by remember { mutableStateOf(initialDuration?.toString() ?: "") }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = Color.White,
        contentColor = f7Gray,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                "Payment Duration",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Poppins,
                color = appBlue,
                modifier = Modifier.padding(top = 12.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DurationInput(
                    duration = selectedDuration,
                    onDurationChange = { selectedDuration = it },
                    frequency = frequency
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
                    thickness = 1.dp,
                    color = Color.Black
                )
            }

            BottomButton(
                text = "Confirm",
                onClick = {
                    scope.launch {
                        selectedDuration.toIntOrNull()?.let {
                            onConfirm(it)
                        }
                        onDismiss()
                    }
                },
                enabled = selectedDuration.isNotBlank(),
                modifier = Modifier
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrequencySelectionBottomDialog(
    show: Boolean,
    selected: BillingFrequency?,
    onDismiss: () -> Unit,
    onSelect: (BillingFrequency) -> Unit
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
                "Select Subscription Frequency",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Poppins,
                color = appBlue
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BillingFrequency.entries.forEachIndexed { index, item ->
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
                    if (index != BillingFrequency.entries.lastIndex) {
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
fun MandateBanner(amount: String, purpose: String, name: String, navController: NavController, frequency: String?=null){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 2.dp,
        color = Color(0xff1F5EE6),
        contentColor = Color.White
    ) {

        val formattedText= buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            ){
                append("₹ $amount")
            }
            frequency?.let {
                withStyle(
                    style = SpanStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                    )
                ) {
                    append("  per $it")
                }
            }
        }

        Column(modifier=Modifier.fillMaxWidth().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier=Modifier.fillMaxWidth()) {
                Text(
                    formattedText,
                    fontFamily = Poppins,
                    maxLines = 1
                )

                Icon(
                    painter = painterResource(Res.drawable.edit_icon),
                    contentDescription = "edit",
                    modifier = Modifier.size(20.dp).clickable(onClick = {
                        navController.popBackStack()
                    }, indication = null, interactionSource = MutableInteractionSource()),
                )
            }

            Text(
                text= buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontSize = 16.sp,
                        color = Color(0xffB5B5B5)
                    )){
                        append("to be collected from ")
                    }

                    withStyle(style = SpanStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )){
                        append(name.capitalize(Locale.current))
                    }

                    withStyle(style = SpanStyle(
                        fontSize = 16.sp,
                        color = Color(0xffB5B5B5)
                    )){
                        append(" for ")
                    }
                    withStyle(style = SpanStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )){
                        append(purpose)
                    }

                },
                fontFamily = Poppins,
                lineHeight = 24.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Composable
fun PaymentSchedule(
    frequency: BillingFrequency?,
    duration: Int?,
    date: Long?,
    onBillingFrequencyClick: () -> Unit,
    onDurationClick: () -> Unit,
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
                placeHolder = "Billing Frequency",
                value = frequency?.label,
                onClick = onBillingFrequencyClick
            )
            PaymentScheduleItem(
                icon = Res.drawable.paymentduration_icon,
                placeHolder = "Payment Duration",
                value = duration?.let {
                    "$it " + frequency.toFrequency().capitalize(Locale.current) +
                            if (frequency != null) "s" else ""
                },
                onClick = onDurationClick
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
fun PaymentScheduleItem(
    icon: DrawableResource,
    placeHolder: String,
    value: String? = null,
    onClick: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxWidth()
            .height(52.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp))
            .clickable(onClick = { onClick() })
        ,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = appBlue
            )

            if (value == null) {
                Text(
                    text = placeHolder,
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            } else {
                Text(
                    text = value,
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = appBlue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}

@Composable
fun RadioButton(selected: Boolean, label: String, onClick: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),) {
        Box(modifier=Modifier.size(22.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = if (selected) appBlue else f7Gray, shape = CircleShape)
            .clickable { onClick() }
        ){
            if (selected){
                Box(modifier=Modifier.fillMaxSize().clip(CircleShape).padding(4.dp).background(appBlue, CircleShape))
            }
        }

        Text(
            text=label,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            fontFamily = Poppins,
            color = Color.Black,
            modifier = Modifier.clickable { onClick() }
        )
    }
}

@Composable
fun DurationInput(
    duration: String,
    onDurationChange: (String) -> Unit,
    frequency: BillingFrequency?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicTextField(
            value = duration,
            onValueChange = {
                if (it.length <= 2) onDurationChange(it.trim())
            },
            textStyle = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                color = Color.Black,
                textAlign = TextAlign.End
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Number box (right aligned)
                    Box(
                        modifier = Modifier.widthIn(max= 44.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        if (duration.isBlank()) {
                            Text(
                                "0",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Poppins,
                                color = Color(0xFFB5B5B5)
                            )
                        }
                        innerTextField()
                    }


                    Text(
                        text= if (frequency==null) "Duration" else (frequency.toFrequency().capitalize(Locale.current)+"s"),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Poppins,
                        color = Color(0xFFB5B5B5)
                    )
                }
            }
        )
    }
}
