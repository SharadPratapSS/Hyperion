package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.arrow_right
import emify.composeapp.generated.resources.icon_about
import emify.composeapp.generated.resources.icon_aboutus
import emify.composeapp.generated.resources.icon_grief
import emify.composeapp.generated.resources.icon_policy
import emify.composeapp.generated.resources.icon_tnc
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.sharad.emify.core.navigation.Routes_AboutUs
import org.sharad.emify.core.navigation.Routes_GreivancePolicy
import org.sharad.emify.core.navigation.Routes_PrivacyPolicy
import org.sharad.emify.core.navigation.Routes_TnC
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.core.util.ChangeBackPress
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.profile.presentation.viewmodels.BusinessScreenViewModel

@Composable
fun AboutScreen(navController: NavController){

    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp))
        {
            TopBar("About EMIfy", onBackClick =  {navController.popBackStack()})
            LazyColumn(modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White, RoundedCornerShape(12.dp))) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().clickable(onClick = {
                        navController.navigate(Routes_AboutUs)
                    })){
                        OptionTile(icon = Res.drawable.icon_about, title = "About EMIfy")
                    }
                }
                item {
                    HorizontalDivider(thickness = 2.dp, color = Color(0xFFF7F7F7), modifier = Modifier.fillMaxWidth())
                }
                item {
                    Box(modifier = Modifier.fillMaxWidth().clickable(onClick = {
                        navController.navigate(Routes_GreivancePolicy)
                    })){
                        OptionTile(icon = Res.drawable.icon_grief, title = "Greivance Policy")
                    }
                }
                item {
                    HorizontalDivider(thickness = 2.dp, color = Color(0xFFF7F7F7), modifier = Modifier.fillMaxWidth())
                }
                item {
                    Box(modifier = Modifier.fillMaxWidth().clickable(onClick = {
                        navController.navigate(Routes_PrivacyPolicy)
                    })){
                        OptionTile(icon = Res.drawable.icon_policy, title = "Privacy Policy")
                    }
                }
                item {
                    HorizontalDivider(thickness = 2.dp, color = Color(0xFFF7F7F7), modifier = Modifier.fillMaxWidth())
                }
                item {
                    Box(modifier = Modifier.fillMaxWidth().clickable(onClick = {
                        navController.navigate(Routes_TnC)
                    })){
                        OptionTile(icon = Res.drawable.icon_tnc, title = "Terms & Conditions")
                    }
                }
            }
        }
    }
}

@Composable
fun OptionTile(icon: DrawableResource, title: String){
    Row (modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)){
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Poppins,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
        )
        Icon(
            painter = painterResource(Res.drawable.arrow_right),
            contentDescription = null,
            modifier = Modifier.size(24.dp).padding(4.dp)
        )
    }
}