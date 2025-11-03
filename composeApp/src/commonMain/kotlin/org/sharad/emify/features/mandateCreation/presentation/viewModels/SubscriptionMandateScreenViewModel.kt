package org.sharad.emify.features.mandateCreation.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.Serializable

class SubscriptionMandateScreenViewModel: ViewModel() {

    private val _billFrequency = MutableStateFlow<BillingFrequency?>(null)
    val billFrequency = _billFrequency.asStateFlow()

    fun setBillFrequency(frequency: BillingFrequency){
        _billFrequency.value = frequency
    }

    private val _duration = MutableStateFlow<Int?>(null)
    val duration = _duration.asStateFlow()

    fun setDuration(duration: Int?){
        _duration.value = duration
    }

    private val _date = MutableStateFlow<Long?>(null)
    val date = _date.asStateFlow()


    fun setDate(date: Long?){
        _date.value = date
    }

    val nextEnabled= combine(billFrequency,duration,date){
        val billableFrequency = it[0]
        val duration = it[1]
        val date = it[2]

        billableFrequency != null && duration != null && date != null
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

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

}

@Serializable
enum class BillingFrequency(val label:String){
    @Serializable
    WEEKLY("Weekly (W)"),
    @Serializable
    MONTHLY("Monthly (M)"),
    @Serializable
    YEARLY("Yearly (Y)")
}

fun BillingFrequency?.toFrequency():String{
    return when(this){
        BillingFrequency.WEEKLY -> "week"
        BillingFrequency.MONTHLY -> "month"
        BillingFrequency.YEARLY -> "year"
        null -> ""
    }
}