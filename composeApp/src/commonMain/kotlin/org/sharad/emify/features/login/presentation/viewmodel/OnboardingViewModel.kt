package org.sharad.emify.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sharad.emify.core.navigation.Routes_HomeScreen
import org.sharad.emify.core.navigation.Routes_PhoneNumberInput
import org.sharad.emify.core.navigation.Routes_WelcomeLoader
import org.sharad.emify.core.networking.NetworkResponse
import org.sharad.emify.core.networking.repository.ProfileRepository
import org.sharad.emify.core.util.Prefs

class OnboardingViewModel(private val repository: ProfileRepository,
    private val prefs: Prefs): ViewModel() {

    private val _loader = MutableStateFlow(false)
    val loader = _loader.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    private val _isEnabled = firstName.combine(lastName){ firstName, lastName ->
        firstName.isNotBlank()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val isEnabled = _isEnabled

    fun updateFirstName(value: String){
        _firstName.value = value
    }

    fun updateLastName(value: String){
        _lastName.value = value
    }

    fun submitData(navController: NavController){
        if (_loader.value) return
        val userId= prefs.getUserId()
        viewModelScope.launch {
            _loader.value=true
            _error.value=null
            try {
                val response= repository.onBoardUser(
                    firstName = _firstName.value,
                    lastName = _lastName.value.ifBlank { null },
                    userId = userId!!
                )
                when(response){
                    is NetworkResponse.Error<*> -> {
                        _error.value=response.error.toString()
                        prefs.setUserId(null)
                        prefs.setPrimaryToken(null)
                        prefs.setRefreshToken(null)
                        prefs.setLoginStatus(false)
                        navController.navigate(Routes_PhoneNumberInput){popUpTo(0){inclusive=true} }
                    }
                    is NetworkResponse.Success<*> -> {
                        prefs.setLoginStatus(true)
                        navController.navigate(Routes_WelcomeLoader){popUpTo(0){inclusive=true} }
                    }
                }
            }catch (e: Exception){
                _error.value=e.toString()
                prefs.setUserId(null)
                prefs.setPrimaryToken(null)
                prefs.setRefreshToken(null)
                prefs.setLoginStatus(false)
                navController.navigate(Routes_PhoneNumberInput){popUpTo(0){inclusive=true} }
            }
            _loader.value=false
        }
    }

    fun resetLogin(navController: NavController) {
        prefs.setUserId(null)
        prefs.setPrimaryToken(null)
        prefs.setRefreshToken(null)
        prefs.setLoginStatus(false)
        navController.navigate(Routes_PhoneNumberInput){popUpTo(0){inclusive=true} }
    }

}