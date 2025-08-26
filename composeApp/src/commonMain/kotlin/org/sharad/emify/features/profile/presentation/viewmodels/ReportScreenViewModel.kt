package org.sharad.emify.features.profile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ReportScreenViewModel: ViewModel() {

    private val _reportText = MutableStateFlow("")
    val reportText = _reportText

    private val _selectedImage= MutableStateFlow<ByteArray?>(null)
    val selectedImage= _selectedImage

    private val _showSuccess=MutableStateFlow(false)
    val showSuccess= _showSuccess

    fun changeText(text:String){
        _reportText.value=text
    }

    fun updateImage(image:ByteArray?){
        _selectedImage.value=image
    }

    fun showSuccess(){
        _showSuccess.value=true
    }

    fun hideSuccess() {
        _showSuccess.value = false

    }


}