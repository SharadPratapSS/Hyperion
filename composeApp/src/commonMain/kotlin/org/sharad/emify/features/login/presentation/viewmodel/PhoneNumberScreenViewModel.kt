package org.sharad.emify.features.login.presentation.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PhoneNumberScreenViewModel: ViewModel() {
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


    fun updateNumber(number: String){
        _number.value=number
    }

    fun updateChecked(){
        _isChecked.value=!(_isChecked.value)
    }
}