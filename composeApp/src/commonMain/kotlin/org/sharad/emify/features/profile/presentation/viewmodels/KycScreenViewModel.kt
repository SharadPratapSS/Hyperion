package org.sharad.emify.features.profile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class KycScreenViewModel: ViewModel() {

    private val _entityType= MutableStateFlow<String?>(null)
    val entityType=_entityType.asStateFlow()

    private val _docType= MutableStateFlow<String?>(null)
    val docType=_docType.asStateFlow()

    private val _adhaarNumber= MutableStateFlow("")
    val adhaarNumber=_adhaarNumber.asStateFlow()

    private val _panNumber= MutableStateFlow("")
    val panNumber=_panNumber.asStateFlow()

    private val _selfie= MutableStateFlow<ByteArray?>(null)
    val selfie=_selfie.asStateFlow()

    private val _gstCertificate= MutableStateFlow<ByteArray?>(null)
    val gstCertificate=_gstCertificate.asStateFlow()

    private val _showAdhaarVerificationField= MutableStateFlow(false)
    val showAdhaarVerificationField = _showAdhaarVerificationField.asStateFlow()

    private val _showPanVerificationField= MutableStateFlow(false)
    val showPanVerificationField = _showPanVerificationField.asStateFlow()

    fun updateEntityType(entity: String){
        _entityType.value=entity
    }

    fun updateDocType(type: String){
        _docType.value=type
    }

    fun updateAdhaarNumber(number: String){
        _adhaarNumber.value=number
    }

    fun updatePanNumber(number: String){
        _panNumber.value=number
    }

    fun updateSelfie(bytes: ByteArray){
        _selfie.value=bytes
    }

    fun updateGstCertificate(bytes: ByteArray){
        _gstCertificate.value=bytes
    }

    fun showAdhaarVerificationField(show: Boolean){
        _showAdhaarVerificationField.value=show
    }

    fun showPanVerificationField(show: Boolean){
        _showPanVerificationField.value=show
    }

    fun hideAdhaarVerificationField(){
        _showAdhaarVerificationField.value=false
    }

    fun hidePanVerificationField(){
        _showPanVerificationField.value=false
    }

    fun clear(){
        _entityType.value=""
        _docType.value=""
        _adhaarNumber.value=""
        _panNumber.value=""
        _selfie.value=null
        _gstCertificate.value=null
        _showAdhaarVerificationField.value=false
        _showPanVerificationField.value=false
    }




}