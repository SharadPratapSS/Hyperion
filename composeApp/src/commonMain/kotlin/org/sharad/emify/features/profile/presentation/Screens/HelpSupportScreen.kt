package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.icon_about
import emify.composeapp.generated.resources.icon_grief
import emify.composeapp.generated.resources.icon_help
import emify.composeapp.generated.resources.icon_issue
import emify.composeapp.generated.resources.icon_policy
import emify.composeapp.generated.resources.icon_tnc
import emify.composeapp.generated.resources.map
import org.sharad.emify.core.navigation.Routes_AboutUs
import org.sharad.emify.core.navigation.Routes_FAQ
import org.sharad.emify.core.navigation.Routes_GreivancePolicy
import org.sharad.emify.core.navigation.Routes_Guide
import org.sharad.emify.core.navigation.Routes_PrivacyPolicy
import org.sharad.emify.core.navigation.Routes_Report
import org.sharad.emify.core.navigation.Routes_TnC
import org.sharad.emify.features.home.presentation.screens.TopBar

@Composable
fun HelpSupportScreen(navController: NavController){

    Box(modifier= Modifier.fillMaxSize().padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp))
        {
            TopBar("Help & Support", onBackClick =  {navController.popBackStack()})
            LazyColumn(modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White, RoundedCornerShape(12.dp))) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().clickable(onClick = {
                        navController.navigate(Routes_FAQ)
                    })){
                        OptionTile(icon = Res.drawable.icon_help, title = "Frequently Asked Questions")
                    }
                }
                item {
                    HorizontalDivider(thickness = 2.dp, color = Color(0xFFF7F7F7), modifier = Modifier.fillMaxWidth())
                }
                item {
                    Box(modifier = Modifier.fillMaxWidth().clickable(onClick = {
                        navController.navigate(Routes_Guide)
                    })){
                        OptionTile(icon = Res.drawable.map, title = "Guide to EMIfy App")
                    }
                }
                item {
                    HorizontalDivider(thickness = 2.dp, color = Color(0xFFF7F7F7), modifier = Modifier.fillMaxWidth())
                }
                item {
                    Box(modifier = Modifier.fillMaxWidth().clickable(onClick = {
                        navController.navigate(Routes_Report)
                    })){
                        OptionTile(icon = Res.drawable.icon_issue, title = "Report an Issue")
                    }
                }
            }
        }
    }
}