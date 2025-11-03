package org.sharad.emify.features.MandateDetailsScreen.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.filter_icon
import emify.composeapp.generated.resources.help_questionmark
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.features.home.presentation.screens.TopBar

@Composable
fun MandateDetailsScreen(navController: NavController,mandateId: String){
    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center)
    {

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        )
        {
            TopBar("Mandate Details", onBackClick = { navController.popBackStack() }, icon = Res.drawable.help_questionmark, onIconClick = {})
            Box(modifier= Modifier.weight(1f)) {
                MandateDetailsLoadedScreen(mandateId)
            }
        }
    }
}

@Composable
fun MandateDetailsLoadedScreen(mandate: String) {

}