package org.sharad.emify.features.mandateCreation.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.util.Hash.combine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.sharad.emify.core.util.MandateOption

class MandateCreationViewModel: ViewModel() {

    val mandateOptions = MandateOption.entries

    private val _selectedOption = MutableStateFlow<MandateOption?>(null)
    val selectedOption: StateFlow<MandateOption?> = _selectedOption.asStateFlow()

    fun updateSelectedOption(option: MandateOption?) {
        _selectedOption.value = option
    }

    private val _collectionAmount = MutableStateFlow("")
    val collectionAmount: StateFlow<String> = _collectionAmount.asStateFlow()

    fun updateCollectionAmount(amount: String) {
        _collectionAmount.value = amount.trim()
    }

    private val _customerName = MutableStateFlow("")
    val customerName = _customerName.asStateFlow()

    fun updateCustomerName(name: String) {
        _customerName.value = name
    }

    private val _customerPhone = MutableStateFlow("")
    val customerPhone = _customerPhone.asStateFlow()

    fun updateCustomerPhone(phone: String) {
        _customerPhone.value = phone.trim()
    }

    private val _purposeOfCollection = MutableStateFlow("")
    val purposeOfCollection = _purposeOfCollection.asStateFlow()

    fun updatePurposeOfCollection(purpose: String) {
        _purposeOfCollection.value = purpose
    }


    val showAmountError = combine(_collectionAmount) { values ->
        val collectionAmount = values[0]

        if (collectionAmount.isBlank()) {
            false // no error shown when field is empty
        } else {
            val amount = collectionAmount.toIntOrNull()
            when {
                amount == null -> true
                amount < 1000 -> true
                else -> false
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )


    val showNumberError = combine(_customerPhone){it->
        val customerPhone = it[0]
        if (customerPhone.isBlank()) return@combine false
        else {
            !(customerPhone.all { amount -> amount.isDigit() }) || customerPhone.length>10
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    val nextEnabled= combine(_selectedOption, _collectionAmount, _customerName, _customerPhone, _purposeOfCollection,showAmountError, showNumberError){

        val collectionAmount = it[1] as String
        val customerName = it[2] as String
        val customerPhone = it[3] as String
        val purposeOfCollection = it[4] as String
        val showAmountError = it[5] as Boolean
        val showNumberError = it[6] as Boolean

        collectionAmount.isNotBlank() && customerName.isNotBlank() && customerPhone.isNotBlank() && customerPhone.length==10 && purposeOfCollection.isNotBlank()&& !showAmountError && !showNumberError
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)



}