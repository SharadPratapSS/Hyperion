package org.sharad.emify.features.profile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.util.Hash.combine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AddBankAccountViewModel: ViewModel() {

    private val _holderName= MutableStateFlow("")
    val holderName=_holderName.asStateFlow()

    private val _accountNumber= MutableStateFlow("")
    val accountNumber=_accountNumber.asStateFlow()

    private val _accountNumberVerify= MutableStateFlow("")
    val accountNumberVerify=_accountNumberVerify.asStateFlow()

    private val _ifscCode= MutableStateFlow("")
    val ifscCode=_ifscCode.asStateFlow()

    private val _branchName= MutableStateFlow("")
    val branchName=_branchName.asStateFlow()

    val isActive= combine(_holderName,_accountNumber,_accountNumberVerify,_ifscCode,_branchName){
        val holderName= it[0]
        val accountNumber= it[1]
        val accountNumberVerify= it[2]
        val ifscCode= it[3]
        val branchName= it[4]

        holderName.isNotBlank() && accountNumber.isNotBlank() && accountNumberVerify.isNotBlank() && ifscCode.isNotBlank() && branchName.isNotBlank()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun updateHolderName(name:String){
        _holderName.value=name
    }
    fun updateAccountNumber(number:String){
        _accountNumber.value=number
    }
    fun updateAccountNumberVerify(number:String){
        _accountNumberVerify.value=number
    }

    fun updateIfscCode(code:String){
        _ifscCode.value=code
    }
    fun updateBranchName(name:String){
        _branchName.value=name
    }


}