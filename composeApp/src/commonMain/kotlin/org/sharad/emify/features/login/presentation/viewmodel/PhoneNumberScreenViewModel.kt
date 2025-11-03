package org.sharad.emify.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sharad.emify.core.navigation.Routes_OTPInput
import org.sharad.emify.core.networking.NetworkResponse
import org.sharad.emify.core.util.logDebug
import org.sharad.emify.core.networking.repository.LoginRepository
import org.sharad.emify.core.util.Prefs

class PhoneNumberScreenViewModel(private val repository: LoginRepository, private val prefs: Prefs): ViewModel() {
    private val _number= MutableStateFlow("")
    val number= _number.asStateFlow()

    private val _isChecked=MutableStateFlow(false)
    val isChecked=_isChecked.asStateFlow()

    private val _isEnabled= combine(_number,_isChecked){it->
        val number=it[0] as String
        val isChecked=it[1] as Boolean
        number.length==10 && isChecked
    }.stateIn(
        scope=viewModelScope,
        initialValue = false,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(3000)
    )

    val isEnabled= _isEnabled

    private val _showErrorMessage= MutableStateFlow(false)
    val showErrorMessage=_showErrorMessage.asStateFlow()

    private val _errorMessage= MutableStateFlow<String?>(null)
    val errorMessage=_errorMessage.asStateFlow()

    private val _loading= MutableStateFlow(false)
    val loading=_loading.asStateFlow()

    fun updateNumber(number: String){
        _number.value=number
    }

    fun updateChecked(){
        _isChecked.value=!(_isChecked.value)
    }

    fun submitNumber(navController: NavController){

        if (_loading.value) return

        _loading.value=true
        viewModelScope.launch {
            val response = repository.initiateAuth(_number.value)
            when(response){
                is NetworkResponse.Success->{
                    logDebug("Initiate Auth Success", response.data.toString())
                    _loading.value=false
                    navController.navigate(Routes_OTPInput(
                        _number.value,
                        response.data.otp.code,
                        userId = response.data.otp.id
                    ))
                }

                is NetworkResponse.Error -> {
                    logDebug("Initiate Auth Error", response.error.toString())
                    _loading.value=false
                    showError(message= response.error.toString())
                }
            }
        }
    }

    fun showError(message: String) {
        viewModelScope.launch {
            _errorMessage.value = message
            _showErrorMessage.value = true
            delay(5000)
            _showErrorMessage.value = false
            _errorMessage.value = null
        }
    }
}