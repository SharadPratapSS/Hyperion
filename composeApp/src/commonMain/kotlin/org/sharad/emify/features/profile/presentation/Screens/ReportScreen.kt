package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.icon_gallery
import emify.composeapp.generated.resources.icon_help
import emify.composeapp.generated.resources.icon_issue
import emify.composeapp.generated.resources.map
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.navigation.Routes_FAQ
import org.sharad.emify.core.navigation.Routes_Guide
import org.sharad.emify.core.navigation.Routes_Report
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.util.ChangeBackPress
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.profile.presentation.viewmodels.ReportScreenViewModel

@Composable
fun ReportScreen(navController: NavController){
    val viewModel: ReportScreenViewModel = koinViewModel()

    val showSuccessDialog by viewModel.showSuccess.collectAsStateWithLifecycle()

    Box(modifier=Modifier.fillMaxSize()){
        if (showSuccessDialog){
            ReportScreenSuccess(
                hideSuccessScreen = { viewModel.hideSuccess() },
                viewModel = viewModel
            )
        }
            ReportScreeMain(
                navController = navController,
                showSuccessScreen = { viewModel.showSuccess() },
                viewModel = viewModel
            )

    }
}

@Composable
fun ReportScreenSuccess(hideSuccessScreen:()->Unit,viewModel: ReportScreenViewModel){
    ChangeBackPress(true, action = { hideSuccessScreen()})
    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ){

    }
}

@Composable
fun ReportScreeMain(navController: NavController, showSuccessScreen:()->Unit,viewModel: ReportScreenViewModel){

    val reportText by viewModel.reportText.collectAsStateWithLifecycle()
    val selected by viewModel.selectedImage.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        )
        {
            TopBar("Report an Issue", onBackClick = { navController.popBackStack() })
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Report your issue here",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color(0xFF909090)
                )
                Box(modifier = Modifier.weight(1f).heightIn(min=32.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White, RoundedCornerShape(15.dp))
                    .border(1.dp, Color(0xFFDCE2EF), RoundedCornerShape(15.dp))
                ){
                    BasicTextField(
                        value = reportText,
                        onValueChange = {viewModel.changeText(it)},
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = Poppins,
                            color = Color.Black
                        )
                    )
                }
                Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                    .background(Color.White, RoundedCornerShape(15.dp))
                    .dashedBorder(
                        color = Color(0xFFDCE2EF),
                        strokeWidth = 2.dp,
                        cornerRadius = 15.dp,
                        dashLength = 10.dp,
                        gapLength = 8.dp
                    ),
                    contentAlignment = Alignment.Center
                )
                {
                    if (selected==null){
                        Column(modifier=Modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.icon_gallery),
                                contentDescription = "Gallery Icon",
                                modifier = Modifier.size(36.dp)
                            )
                            Text(
                                "Add a Screenshot",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                "Please add a screenshot of problem you are facing for better understanding",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                lineHeight = 14.sp,
                                color = Color(0xFF808794),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Box(modifier=Modifier.fillMaxWidth().padding(bottom = 4.dp)){
                    BottomButton(text = "Submit", onClick = {
                        if (reportText.isNotEmpty()) {
                            showSuccessScreen()
                        }
                    }, modifier = Modifier)
                }
            }
        }
    }
}

fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Dp,
    cornerRadius: Dp = 0.dp,
    dashLength: Dp = 10.dp,
    gapLength: Dp = 10.dp
) = this.then(
    Modifier.drawBehind {
        val stroke = strokeWidth.toPx()
        val dash = dashLength.toPx()
        val gap = gapLength.toPx()

        drawRoundRect(
            color = color,
            size = size,
            style = Stroke(
                width = stroke,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(dash, gap), 0f)
            ),
            cornerRadius = CornerRadius(cornerRadius.toPx())
        )
    }
)