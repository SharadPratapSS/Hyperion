package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.arrow_down
import emify.composeapp.generated.resources.arrow_right
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.core.util.ChangeBackPress
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.profile.presentation.viewmodels.BusinessScreenViewModel


@Composable
fun BusinessAddressScreen(backPress:()->Unit, viewModel: BusinessScreenViewModel){

    val ad1 by viewModel.aLine1.collectAsStateWithLifecycle()
    val ad2 by viewModel.aLine2.collectAsStateWithLifecycle()
    val city by viewModel.city.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val landmark by viewModel.landmark.collectAsStateWithLifecycle()
    val pincode by viewModel.pincode.collectAsStateWithLifecycle()
    val enabled by viewModel._addressButtonEnabled.collectAsStateWithLifecycle()
    ChangeBackPress(true, { backPress() })
    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp))
        {
            TopBar("Business Address", onBackClick =  {backPress()})
            LazyColumn(modifier=Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp))
            {
                item {
                    FormItem(value = ad1, placeholder ="Address Line 1", onValueChange = {viewModel.updateALine1(it)})
                }
                item {
                    FormItem(value = ad2, placeholder ="Address Line 2", onValueChange = {viewModel.updateALine2(it)})
                }
                item {
                    FormItem(value = pincode, placeholder ="PIN Code", onValueChange = {viewModel.updatePincode(it)})
                }
                item {
                    FormItem(value = city, placeholder ="City", onValueChange = {viewModel.updateCity(it)})
                }
                item {
                    FormItem(value = state, placeholder ="State", onValueChange = {viewModel.updateState(it)})
                }

                item {
                    FormItem(value = landmark, placeholder ="Landmark(Optional)", onValueChange = {viewModel.updateLandmark(it)})
                }
                item {
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }
    }
}