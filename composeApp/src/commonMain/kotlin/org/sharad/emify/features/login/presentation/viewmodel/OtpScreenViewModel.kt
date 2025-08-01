package org.sharad.emify.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.math.max

class OtpScreenViewModel: ViewModel() {

    private val maxOtpLength=6

    private val _otp = MutableStateFlow("")
    val otp = _otp.asStateFlow()

    val isEnabled = _otp.map {
        it.length == maxOtpLength
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    fun updateOtp(otp: String) {
        if (otp.length <= maxOtpLength){
            _otp.value = otp
        }
    }

}