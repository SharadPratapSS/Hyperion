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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.icon_help
import emify.composeapp.generated.resources.icon_issue
import emify.composeapp.generated.resources.map
import org.sharad.emify.core.navigation.Routes_AddBankAccountScreen
import org.sharad.emify.core.navigation.Routes_FAQ
import org.sharad.emify.core.navigation.Routes_Guide
import org.sharad.emify.core.navigation.Routes_Report
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appGray
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.features.home.presentation.screens.TopBar


@Composable
fun BankAccountScreen(navController: NavController){

    var bankAccount by remember { mutableStateOf("STATE BANK OF INDIA - xxxx4772") }

    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp,end=20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp))
        {
            TopBar("Bank Account", onBackClick =  {navController.popBackStack()})
            Column(modifier=Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Text("Please add your linked bank account for verification",
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 18.sp,
                    color = Color(0xFF909090),
                    modifier=Modifier.padding(vertical = 4.dp)
                    )
                Box(modifier=Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                    .background(Color.White, RoundedCornerShape(12.dp))
                ){
                    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Current Settlement Bank account",
                            fontSize = 14.sp,
                            fontFamily = Poppins,
                            color = Color(0xFF909090))
                        Column(modifier=Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(4.dp),){
                            Text(
                                bankAccount,
                                fontSize = 15.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            BottomButton("Add New Bank Account", onClick = { navController.navigate(
                Routes_AddBankAccountScreen)}, modifier = Modifier)
        }
    }
}