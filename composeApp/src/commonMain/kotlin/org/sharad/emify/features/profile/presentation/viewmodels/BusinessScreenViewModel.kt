package org.sharad.emify.features.profile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class BusinessScreenViewModel: ViewModel() {
    private val _businessName= MutableStateFlow("")
    val businessName= _businessName.asStateFlow()

    private val _businessEmail= MutableStateFlow("")
    val businessEmail = _businessEmail.asStateFlow()

    private val _businessAddress= MutableStateFlow("")
    val businessAddress  = _businessAddress.asStateFlow()

    private val _businessType= MutableStateFlow("")
    val businessType= _businessType.asStateFlow()

    private val _businessCategory= MutableStateFlow("")
    val businessCategory= _businessCategory.asStateFlow()

    private val _aLine1= MutableStateFlow("")
    val aLine1= _aLine1.asStateFlow()

    private val _aLine2= MutableStateFlow("")
    val aLine2= _aLine2.asStateFlow()

    private val _city= MutableStateFlow("")
    val city= _city.asStateFlow()

    private val _state= MutableStateFlow("")
    val state= _state.asStateFlow()

    private val _landmark= MutableStateFlow("")
    val landmark= _landmark.asStateFlow()

    private val _pincode= MutableStateFlow("")
    val pincode= _pincode.asStateFlow()

    val _addressButtonEnabled=  combine(_aLine1, _aLine2, _city, _state, _pincode){values->
        values.all { it.isNotEmpty() }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun updateBusinessName(name:String){
        _businessName.value=name
    }

    fun updateBusinessEmail(email:String){
        _businessEmail.value=email
    }

    fun updateBusinessAddress(address:String){
        _businessAddress.value=address
    }

    fun updateBusinessType(type:String){
        _businessType.value=type
    }

    fun updateBusinessCategory(category:String){
        _businessCategory.value=category

    }

    fun updateALine1(line1:String){
        _aLine1.value=line1
    }

    fun updateALine2(line2:String){
        _aLine2.value=line2
    }

    fun updateCity(city:String){
        _city.value=city
    }

    fun updateState(state:String){
        _state.value=state
    }

    fun updateLandmark(landmark:String){
        _landmark.value=landmark
    }

    fun updatePincode(pincode:String){
        _pincode.value=pincode
    }
}