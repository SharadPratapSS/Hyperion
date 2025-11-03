package org.sharad.emify.features.MandateDetailsScreen.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.filter_icon
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.domain.Mandate.MandateItem
import org.sharad.emify.core.domain.Mandate.MandateStatus
import org.sharad.emify.core.navigation.Routes_MandateDetailsScreen
import org.sharad.emify.core.ui.SharedComponents.MandateItemActive
import org.sharad.emify.core.ui.SharedComponents.MandateItemExpired
import org.sharad.emify.core.ui.SharedComponents.MandateItemFailed
import org.sharad.emify.core.ui.SharedComponents.MandateItemFinished
import org.sharad.emify.core.ui.SharedComponents.MandateItemPending
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.features.MandateDetailsScreen.presentation.viewmodel.AllMandateScreenViewModel
import org.sharad.emify.features.home.presentation.screens.TopBar

@Composable
fun AllMandatesScreen(navController: NavController) {

    val viewModel: AllMandateScreenViewModel= koinViewModel()
    val mandateList by viewModel.mandateList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchMandates()
    }

    Box(modifier= Modifier.fillMaxSize().background(f7Gray),
        contentAlignment = Alignment.Center)
    {

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        )
        {
            TopBar("All Mandates", onBackClick = { navController.popBackStack() }, icon = Res.drawable.filter_icon, onIconClick = {})
            Box(modifier = Modifier.weight(1f)) {
               MandateListSuccess(mandateList,navController)
            }
        }
    }
}

@Composable
fun MandateListSuccess(mandateList: List<MandateItem>, navController: NavController){

    Surface(
        modifier = Modifier.fillMaxWidth().padding(top=12.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        ) {
            itemsIndexed(items = mandateList, key ={idx,it-> it.name} ) {idx,it->
                    if (idx == 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                Box(modifier=Modifier.fillMaxWidth().clickable(onClick = {
                    navController.navigate(Routes_MandateDetailsScreen(it.id))
                })){
                    when (it.status) {
                        MandateStatus.Active -> {
                            MandateItemActive(it)
                        }

                        MandateStatus.Failed -> {
                            MandateItemFailed(it)
                        }

                        MandateStatus.Finished -> {
                            MandateItemFinished(it)
                        }

                        MandateStatus.Pending -> {
                            MandateItemPending(it)
                        }

                        MandateStatus.Expired -> {
                            MandateItemExpired(it)
                        }
                    }
                }
                    if (mandateList.lastIndex != idx) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            color = f7Gray,
                            thickness = 2.dp
                        )
                    } else {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
            }
        }
    }
}