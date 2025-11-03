package org.sharad.emify.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.features.home.presentation.screens.TopBar

@Composable
fun Screen(navController: NavController){
    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center)
    {

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        )
        {
            TopBar("Complete your KYC", onBackClick = { navController.popBackStack() })
            Box(modifier= Modifier.weight(1f)) {

            }
        }
    }
}