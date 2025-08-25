package org.sharad.emify.features.profile.presentation.Screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.arrow_down
import emify.composeapp.generated.resources.arrow_right
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appGray
import org.sharad.emify.features.home.presentation.screens.DividerItem
import org.sharad.emify.features.home.presentation.screens.TopBar
import org.sharad.emify.features.profile.presentation.viewmodels.BusinessScreenViewModel

@Composable
fun BusinessDetailsScreen(navController: NavController){
    val viewModel: BusinessScreenViewModel = koinViewModel()
    var addressScreen by remember{mutableStateOf(false)}


    Box(modifier=Modifier.fillMaxSize()){
        if (addressScreen){
            BusinessAddressScreen({addressScreen=false},viewModel)
        }else{
            DetailsScreen(viewModel,navController,{addressScreen=true})
        }
    }
}

@Composable
fun DetailsScreen(viewModel: BusinessScreenViewModel,navController: NavController,showAddressScreen:()->Unit ){
    val businessName by viewModel.businessName.collectAsStateWithLifecycle()
    val businessEmail by viewModel.businessEmail.collectAsStateWithLifecycle()
    val businessAddress by viewModel.businessAddress.collectAsStateWithLifecycle()
    val businessType by viewModel.businessType.collectAsStateWithLifecycle()
    val businessCategory by viewModel.businessCategory.collectAsStateWithLifecycle()


    val businessTypes = listOf("Wholesaler", "Retailer", "Reseller", "Manufacturer", "Landlord", "Consultant/Advisor", "Service Provider", "Freelancer", "E-commerce Business", "Aggregator", "Franchise Owner", "Dropshipper", "Professional Firm", "Event Organiser")
    val businessCategories = listOf("FMCG", "Electronics", "Clothing & Apparel", "Industrial Supplies", "Agricultural Products", "Pharmaceuticals", "Furniture & Home Goods")

    Box(modifier= Modifier.fillMaxSize().padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxSize().padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)) {
            TopBar("Business Details", {navController.popBackStack()})
            LazyColumn(modifier=Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    FormItem(value = businessName,label="Business Name", onValueChange = {viewModel.updateBusinessName(it)}, placeholder = "Write your business name")
                }
                item {
                    FormItem(value = businessEmail,label="Business Email Address", onValueChange = {viewModel.updateBusinessEmail(it)}, placeholder = "Write your business email address")
                }
                item {
                    FormItem(
                        value = businessAddress,
                        label = "Business Address",
                        onValueChange = { viewModel.updateBusinessAddress(it) },
                        placeholder = "Add your address",
                        enable = false,
                        icon = Res.drawable.arrow_right,
                        iconClick = {showAddressScreen()}
                    )
                }
                item {
                    DropDownField(
                        value = businessType,
                        label = "Business Type",
                        onValueChange = { viewModel.updateBusinessType(it) },
                        placeholder = "Select Your Business Type",
                        enable = false,
                        icon = Res.drawable.arrow_down,
                        selectionList = businessTypes
                    )
                }

                item {
                    DropDownField(
                        value = businessCategory,
                        label = "Business Category",
                        onValueChange = { viewModel.updateBusinessCategory(it) },
                        placeholder = "Select Your Business Category",
                        enable = false,
                        icon = Res.drawable.arrow_down,
                        selectionList = businessCategories
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }
    }
}

@Composable
fun FormItem(value: String, onValueChange: (String) -> Unit,
             placeholder: String,
             label: String?=null,
             enable:Boolean=true,
             icon: DrawableResource ?=null,
             iconClick: (() -> Unit)?=null,
             ){
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        label?.let{it
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth().padding(start = 2.dp),
                fontFamily = Poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
        }

            BasicTextField(
                enabled = enable,
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    color = Color.Black
                ),
                modifier = Modifier
//                    .padding(top = 4.dp, bottom = 12.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(12.dp),
                        spotColor = Color(0xFFDEE2F6BF)
                    )
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .then(
                        if (icon != null) {
                            Modifier.clickable(onClick = { iconClick?.invoke() })
                        } else
                            Modifier
                    ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(1f)
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    fontFamily = Poppins,
                                    fontSize = 16.sp,
                                    color = Color(0xFFB5B5B5)
                                )
                            }
                            innerTextField()
                        }
                        icon?.let { it ->
                            Image(
                                painter = painterResource(it),
                                contentDescription = "icon",
                                modifier = Modifier.padding(horizontal = 16.dp).size(16.dp)
                            )
                        }
                    }
                }
            )
    }
}

@Composable
fun DropDownField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String? = null,
    enable: Boolean = true,
    icon: DrawableResource? = null,
    iconClick: (() -> Unit)? = null,
    selectionList: List<String>? = null,
    selectItem:(()-> Unit)?=null
) {
    var showList by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        label?.let {
            it
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth().padding(start = 2.dp),
                fontFamily = Poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        Box(modifier = Modifier.fillMaxWidth()) {

            Column(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).offset(y=10.dp).background(Color.White, RoundedCornerShape(12.dp)))
            {
                AnimatedVisibility(showList){
                    if (selectionList!=null){
                        Column{
                            Spacer(modifier = Modifier.height(44.dp))
                            selectionList.forEachIndexed {index, it ->
                                Text(
                                    text = it,
                                    modifier = Modifier.fillMaxWidth().padding( 16.dp).clickable(onClick = {
                                        onValueChange.invoke(it)
                                        showList=false
                                    },
                                        indication = null,
                                        interactionSource = MutableInteractionSource()
                                    ),
                                    fontFamily = Poppins,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                if (index<selectionList.size-1){
                                    HorizontalDivider(thickness = 2.dp, color = Color(0xFFF7F7F7), modifier = Modifier.fillMaxWidth())
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }else{
                        Text(
                            text = "No Selection Available",
                            modifier = Modifier.fillMaxWidth().padding(start = 2.dp),
                            fontFamily = Poppins,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }
                }
            }

            BasicTextField(
                enabled = enable,
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    color = Color.Black
                ),
                modifier = Modifier
//                    .padding(top = 4.dp, bottom = 12.dp)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(12.dp),
                        spotColor = Color(0xFFDEE2F6BF)
                    )
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .clickable(onClick = { showList = !showList }),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(1f)
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    fontFamily = Poppins,
                                    fontSize = 16.sp,
                                    color = Color(0xFFB5B5B5)
                                )
                            }
                            innerTextField()
                        }
                        val rotation by animateFloatAsState(
                            targetValue = if (showList) 180f else 0f,
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            ), label = ""
                        )
                        icon?.let { it ->
                            Image(
                                painter = painterResource(it),
                                contentDescription = "icon",
                                modifier = Modifier.padding(horizontal = 16.dp).size(16.dp).graphicsLayer{
                                    rotationZ=rotation
                                }
                            )
                        }
                    }
                }
            )
        }
    }
}


