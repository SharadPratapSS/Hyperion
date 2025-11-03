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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.nach_logo
import emify.composeapp.generated.resources.upi_logo_vector
import org.jetbrains.compose.resources.painterResource
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.SharedComponents.DashedDivider
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.core.util.formatMillisToSlashDate
import org.sharad.emify.core.util.formatMoney
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.mandateCreation.presentation.viewModels.AmountCollectionMethod
import org.sharad.emify.features.mandateCreation.presentation.viewModels.BillingFrequency
import org.sharad.emify.features.mandateCreation.presentation.viewModels.WholeBillingFrequency
import org.sharad.emify.features.mandateCreation.presentation.viewModels.toFrequency

@Composable
fun ConfirmWholeMandateScreen(
    navController: NavController,
    name: String,
    phone: String,
    purpose: String,
    amount: String,
    installmentsNumber: Int?,
    installmentDate: Long?,
    otpCollectionDate: Long?,
    installmentFrequency: WholeBillingFrequency?,
    paymentType: AmountCollectionMethod,
) {
    Box(
        modifier = Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.BottomCenter
    )
    {

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp)
        )
        {
            Column(
                modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp).weight(1f),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            )
            {
                TopBar("Confirm Mandate", onBackClick = { navController.popBackStack() })
                Box(modifier = Modifier.weight(1f)) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))
                        MandateBanner(
                            formatMoney(amount),
                            purpose,
                            name,
                            navController = navController,
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        when(paymentType){
                            AmountCollectionMethod.ONE_TIME_PAYMENT -> {
                                CalculationBanner(
                                    amount = amount,
                                    duration = 1,
                                    date = otpCollectionDate?: 0,
                                    extraText = "to be collected on ",
                                    onClick = {navController.popBackStack()}
                                )
                            }
                            AmountCollectionMethod.INSTALLMENT -> {
                                CalculationBanner(
                                    amount = amount,
                                    duration = installmentsNumber?: 0,
                                    date = installmentDate?: 0,
                                    extraText = "to be collected on ",
                                    onClick = {navController.popBackStack()}

                                )
                            }
                        }


                        Text(
                            "Payment will be collected via any of these methods",
                            fontSize = 14.sp,
                            color = Color(0xffB5B5B5),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        PaymentMethodRow()
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            when(paymentType){
                AmountCollectionMethod.ONE_TIME_PAYMENT -> {
                    val platformServiceFee=75
                    WholeMandateReceiptRow(
                        tcaValue = "₹ ${formatMoney(amount)}",
                        platformServiceFee = platformServiceFee,
                        saLabel = "Settlement Amount after collection",
                        saValue = "₹ ${formatMoney(amount.toInt().plus(platformServiceFee).toString())}",
                        onClick = {}
                    )
                }
                AmountCollectionMethod.INSTALLMENT -> {
                    val platformServiceFee=75
                    WholeMandateReceiptRow(
                        tcaValue = "$installmentsNumber installments of ₹ ${formatMoney(amount)}",
                        platformServiceFee = platformServiceFee,
                        saLabel = "Settlement Amount per installment",
                        saValue = "₹ ${formatMoney(amount.toInt().plus(platformServiceFee).toString())}",
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
fun WholeMandateReceiptRow(tcaValue: String, platformServiceFee:Int, saLabel:String, saValue:String, onClick:()->Unit) {


    Box(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
    ){
        Column(modifier=Modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Total Collection Amount",
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        color = Color(0xffB5B5B5)
                    )
                    Text(
                        text = tcaValue,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        color = Color(0xffB5B5B5)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Platform Service Fee per payment",
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        color = Color(0xffB5B5B5)
                    )
                    Text(
                        text = "₹ $platformServiceFee",
                        fontSize = 12.sp,
                        color = Color(0xffB5B5B5)
                    )
                }
                DashedDivider(modifier=Modifier.fillMaxWidth(), thickness = 2.dp, dashWidth = 20f, gapWidth = 20f)
            }

            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Text(saLabel,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(letterSpacing = (-0.05).em)
                )
                Text(
                    text = saValue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            BottomButton(
                "Generate Link",
                onClick = {onClick()},
                modifier = Modifier,
                enabled = true
            )

        }
    }
}
