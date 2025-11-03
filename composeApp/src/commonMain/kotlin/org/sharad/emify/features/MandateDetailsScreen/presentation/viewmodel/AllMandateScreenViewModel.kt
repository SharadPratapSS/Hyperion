package org.sharad.emify.features.MandateDetailsScreen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sharad.emify.core.domain.Mandate.MandateItem
import org.sharad.emify.core.domain.Mandate.MandateStatus

class AllMandateScreenViewModel: ViewModel() {

    val _mandateList= MutableStateFlow<List<MandateItem>>(emptyList())
    val mandateList=_mandateList.asStateFlow()

    fun fetchMandates(){
        _mandateList.value=mandates
    }

}

val mandates = listOf(
    MandateItem("M001", "Electricity Bill", "Monthly power supply charges", "₹1500", "₹0", MandateStatus.Active),
    MandateItem("M002", "Water Bill", "Quarterly water usage charges", "₹600", "₹0", MandateStatus.Finished),
    MandateItem("M003", "Internet Plan", "High-speed fiber connection", "₹1200", "₹0", MandateStatus.Active),
    MandateItem("M004", "Credit Card EMI", "Monthly EMI for credit card purchase", "₹5000", "₹1000", MandateStatus.Pending),
    MandateItem("M005", "Car Loan", "Vehicle loan monthly installment", "₹10000", "₹2000", MandateStatus.Active),
    MandateItem("M006", "Home Loan", "Mortgage monthly repayment", "₹25000", "₹0", MandateStatus.Active),
    MandateItem("M007", "Mobile Recharge", "Prepaid mobile plan renewal", "₹399", "₹0", MandateStatus.Finished),
    MandateItem("M008", "Netflix Subscription", "Monthly OTT subscription", "₹650", "₹0", MandateStatus.Active),
    MandateItem("M009", "Gym Membership", "Quarterly fitness membership", "₹2000", "₹0", MandateStatus.Expired),
    MandateItem("M010", "Insurance Premium", "Yearly life insurance policy", "₹15000", "₹0", MandateStatus.Finished),
    MandateItem("M011", "School Fees", "Quarterly tuition fees", "₹12000", "₹3000", MandateStatus.Pending),
    MandateItem("M012", "Amazon Prime", "Annual subscription", "₹1499", "₹0", MandateStatus.Finished),
    MandateItem("M013", "Spotify Premium", "Monthly music subscription", "₹129", "₹0", MandateStatus.Active),
    MandateItem("M014", "Electric Scooter EMI", "Loan repayment for scooter", "₹4500", "₹1500", MandateStatus.Failed),
    MandateItem("M015", "Property Tax", "Yearly municipal property tax", "₹8000", "₹0", MandateStatus.Expired)
)
