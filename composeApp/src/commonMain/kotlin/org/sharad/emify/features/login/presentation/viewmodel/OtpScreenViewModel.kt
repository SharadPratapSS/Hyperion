package org.sharad.emify.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sharad.emify.core.navigation.Routes_HomeScreen
import org.sharad.emify.core.navigation.Routes_OnboardingForm
import org.sharad.emify.core.navigation.Routes_WelcomeLoader
import org.sharad.emify.core.networking.NetworkResponse
import org.sharad.emify.core.util.Prefs
import org.sharad.emify.features.login.data.OtpValidationSuccessDto
import org.sharad.emify.core.networking.repository.LoginRepository
import org.sharad.emify.core.networking.repository.ProfileRepository
import org.sharad.emify.features.login.data.ProfileInfoDto

class OtpScreenViewModel(private val repository: LoginRepository,
                         private val prefs: Prefs, private val profileRepository: ProfileRepository): ViewModel() {

    private val maxOtpLength=6

    private val _loader = MutableStateFlow(false)
    val loader = _loader.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


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

    fun validateOtp(userId: String, navController: NavController){
        viewModelScope.launch {
            _loader.value=true
            _error.value=null
            val response= repository.validateOtp(_otp.value, userId)
            when(response){
                is NetworkResponse.Error<*> -> {
                    _error.value=response.error.toString()
                }
                is NetworkResponse.Success<OtpValidationSuccessDto> -> {
                    prefs.setPrimaryToken(response.data.primary_token)
                    prefs.setRefreshToken(response.data.refresh_token)
                    checkUser(userId,navController)
                }
            }
            _loader.value=false
        }
    }

    suspend fun checkUser(userId: String,navController: NavController){
        try {
            val response= profileRepository.getProfileInfo(userId)
            when(response){
                is NetworkResponse.Error<*> -> {
                    _error.value=response.error.toString()
                }
                is NetworkResponse.Success<ProfileInfoDto> -> {
                    if (response.data.first_name==null){
                        navController.navigate(Routes_OnboardingForm){popUpTo(0){inclusive=true} }
                    }
                    else{
                        navController.navigate(Routes_WelcomeLoader){popUpTo(0){inclusive=true} }
                    }
                }
            }
        }catch (e: Exception){
            _error.value=e.toString()
            _loader.value=false
        }
    }

}