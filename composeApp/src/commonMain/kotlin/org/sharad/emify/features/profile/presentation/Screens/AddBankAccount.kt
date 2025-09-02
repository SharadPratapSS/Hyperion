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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.profile.presentation.viewmodels.AddBankAccountViewModel

@Composable
fun AddBankAccount(navController: NavController){

    val viewModel: AddBankAccountViewModel = koinViewModel()

    val holderName by viewModel.holderName.collectAsStateWithLifecycle()
    val accountNumber by viewModel.accountNumber.collectAsStateWithLifecycle()
    val accountNumberCheck by viewModel.accountNumberVerify.collectAsStateWithLifecycle()
    val ifsc by viewModel.ifscCode.collectAsStateWithLifecycle()
    val branch by viewModel.branchName.collectAsStateWithLifecycle()

    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
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

                LazyColumn(modifier = Modifier.weight(1f)) {

                    item{Spacer(modifier=Modifier.height(4.dp))}
                    item{ FormItem(value = holderName, onValueChange = {viewModel.updateHolderName(it)}, placeholder = "Account Holder Name") }
                    item{Spacer(modifier=Modifier.height(16.dp))}
                    item{ FormItem(value = accountNumber, onValueChange = {viewModel.updateAccountNumber(it)}, placeholder = "Account Number") }
                    item{Spacer(modifier=Modifier.height(16.dp))}
                    item{ FormItem(value = accountNumberCheck, onValueChange = {viewModel.updateAccountNumberVerify(it)}, placeholder = "Re-Enter Account Number") }
                    item{Spacer(modifier=Modifier.height(16.dp))}
                    item{ FormItem(value = ifsc, onValueChange = {viewModel.updateIfscCode(it)}, placeholder = "IFSC Code") }
                    item{Spacer(modifier=Modifier.height(16.dp))}
                    item{ FormItem(value = branch, onValueChange = {viewModel.updateBranchName(it)}, placeholder = "Branch Name") }
                    item{Spacer(modifier=Modifier.height(8.dp))}

                }

            }
            BottomButton("Add New Bank Account", onClick = {}, modifier = Modifier)
        }
    }
}