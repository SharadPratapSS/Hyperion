package org.sharad.emify.features.mandateCreation.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.Serializable

class WholeMandateScreenViewModel: ViewModel() {

    private val _installmentFrequency = MutableStateFlow<WholeBillingFrequency?>(null)
    val installmentFrequency = _installmentFrequency.asStateFlow()

    fun setBillFrequency(frequency: WholeBillingFrequency){
        _installmentFrequency.value = frequency
    }

    private val _installmentNumbers = MutableStateFlow<Int?>(null)
    val installmentNumbers = _installmentNumbers.asStateFlow()

    fun setDuration(duration: Int?){
        _installmentNumbers.value = duration
    }

    private val _installmentDate = MutableStateFlow<Long?>(null)
    val installmentDate = _installmentDate.asStateFlow()


    fun setDate(date: Long?){
        _installmentDate.value = date
    }



    private val _showBFSelection = MutableStateFlow(false)
    val showBFSelection = _showBFSelection.asStateFlow()

    fun setShowBFSelection(show: Boolean){
        _showBFSelection.value = show
    }

    private val _showDurationSelection = MutableStateFlow(false)
    val showDurationSelection = _showDurationSelection.asStateFlow()

    fun setShowDurationSelection(show: Boolean){
        _showDurationSelection.value = show
    }

    private val _showDateSelection = MutableStateFlow(false)
    val showDateSelection = _showDateSelection.asStateFlow()

    fun setShowDateSelection(show: Boolean){
        _showDateSelection.value = show
    }

    val _collectionType= MutableStateFlow<AmountCollectionMethod?>(null)
    val collectionType = _collectionType.asStateFlow()

    fun setCollectionType(type:AmountCollectionMethod){
        _collectionType.value = type
    }

    private val _otpSelectionDate = MutableStateFlow<Long?>(null)
    val otpSelectionDate = _otpSelectionDate.asStateFlow()

    fun setOtpSelectionDate(date:Long?){
        _otpSelectionDate.value = date
    }

    private val _showOtpDateSelector = MutableStateFlow(false)
    val showOtpDateSelector = _showOtpDateSelector.asStateFlow()

    fun setShowOtpDateSelector(show:Boolean){
        _showOtpDateSelector.value = show
    }

    val nextEnabled= combine(installmentFrequency,installmentNumbers,installmentDate, _collectionType, otpSelectionDate){
        val billableFrequency = it[0]
        val duration = it[1]
        val date = it[2]
        val collectionType = it[3]
        val otpDate = it[4]

        if (collectionType==null) return@combine false
        else{
            if (collectionType==AmountCollectionMethod.ONE_TIME_PAYMENT){
                return@combine otpDate != null
            }
            else {
                return@combine billableFrequency != null && duration != null && date != null
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

}

@Serializable
enum class AmountCollectionMethod(val label:String){
    @Serializable
    ONE_TIME_PAYMENT("One Time Payment"),
    @Serializable
    INSTALLMENT("Installment")

}


@Serializable
enum class WholeBillingFrequency(val label:String){
    @Serializable
    DAILY("Daily (D)"),
    @Serializable
    WEEKLY("Weekly (W)"),
    @Serializable
    MONTHLY("Monthly (M)"),
    @Serializable
    YEARLY("Yearly (Y)")
}

fun WholeBillingFrequency?.toFrequency():String{
    return when(this){
        WholeBillingFrequency.DAILY -> "day"
        WholeBillingFrequency.WEEKLY -> "week"
        WholeBillingFrequency.MONTHLY -> "month"
        WholeBillingFrequency.YEARLY -> "year"
        null -> ""
    }
}