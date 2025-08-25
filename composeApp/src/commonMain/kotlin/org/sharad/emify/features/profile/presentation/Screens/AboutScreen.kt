package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.sharad.emify.core.util.ChangeBackPress
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.profile.presentation.viewmodels.BusinessScreenViewModel

@Composable
fun AboutScreen(navController: NavController){

    Box(modifier= Modifier.fillMaxSize().padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp))
        {
            TopBar("Business Address", onBackClick =  {navController.popBackStack()})
            Column(modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White, RoundedCornerShape(12.dp))) {
                
            }
        }
    }
}