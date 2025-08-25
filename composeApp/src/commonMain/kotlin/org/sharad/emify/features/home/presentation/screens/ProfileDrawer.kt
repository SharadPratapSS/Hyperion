package org.sharad.emify.features.home.presentation.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.arrow_right
import emify.composeapp.generated.resources.back_arrow
import emify.composeapp.generated.resources.icon_aboutus
import emify.composeapp.generated.resources.icon_bank
import emify.composeapp.generated.resources.icon_business
import emify.composeapp.generated.resources.icon_help
import emify.composeapp.generated.resources.icon_kyc
import emify.composeapp.generated.resources.icon_lock
import emify.composeapp.generated.resources.icon_plans
import emify.composeapp.generated.resources.icon_refer
import emify.composeapp.generated.resources.info_icon
import emify.composeapp.generated.resources.upi_footer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sharad.emify.core.navigation.Routes_BusinessDetails
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.core.ui.theme.f7Gray

@Composable
fun ProfileDrawer(navController: NavHostController) {
    val tempImg="https://img.freepik.com/premium-vector/man-professional-business-casual-young-avatar-icon-illustration_1277826-623.jpg"
    Box(modifier= Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxHeight().padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {
            TopBar("My Profile", {navController.popBackStack()})
            ProfileCard(tempImg,"Sharad Singh", "9198663022")
            ProfileOptionList(modifier=Modifier.weight(1f),navController)
            Image(painter = painterResource(Res.drawable.upi_footer), contentDescription = "footer", modifier = Modifier.padding(bottom = 12.dp))
        }
    }
}

@Composable
fun ProfileOptionList(modifier: Modifier, navHostController: NavHostController){

    val menuOptions = listOf(
        MenuOptions("EMify Plan", "Automate collections at ₹0!", Res.drawable.icon_plans,{}),
        MenuOptions("Business Details", "Showcase your business to customers", Res.drawable.icon_business,{navHostController.navigate(
            Routes_BusinessDetails
        )}),
        MenuOptions("Bank Accounts", "Manage and set your primary bank", Res.drawable.icon_bank,{}),
        MenuOptions("KYC - Profile Verification", "Faster settlements & priority support", Res.drawable.icon_kyc,{}),
        MenuOptions("App Lock", "Add an extra layer of security", Res.drawable.icon_lock,{}),
        MenuOptions("Refer & Earn", "Invite friends to EMIFY & earn rewards!", Res.drawable.icon_refer,{}),
        MenuOptions("About EMify", "Terms, privacy policy, and legal info", Res.drawable.icon_aboutus,{}),
        MenuOptions("Help & Support", "FAQs and customer support at your fingertips", Res.drawable.icon_help,{})
    )

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item { ProfileMenuItem(menuOptions[0]) }
            item { DividerItem() }
            item { ProfileMenuItem(menuOptions[1]) }
            item { DividerItem() }
            item { ProfileMenuItem(menuOptions[2]) }
            item { DividerItem() }
            item { ProfileMenuItem(menuOptions[3]) }
            item { DividerItem() }
            item { ProfileMenuItemWithToggle(menuOptions[4]) }
            item { DividerItem() }
            item { ProfileMenuItem(menuOptions[5]) }
            item { DividerItem() }
            item { ProfileMenuItem(menuOptions[6]) }
            item { DividerItem() }
            item { ProfileMenuItem(menuOptions[7]) }
        }
    }
}

@Composable
fun DividerItem() =
    HorizontalDivider(Modifier.fillMaxWidth().padding(horizontal = 20.dp), color = f7Gray, thickness = 2.dp)

@Composable
fun ProfileMenuItem(menuOptions: MenuOptions){
    Box(Modifier.fillMaxWidth().clickable { menuOptions.onClick() }){
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp, horizontal = 24.dp),
        ) {
            Icon(
                painter = painterResource(menuOptions.icon),
                tint = appBlue,
                contentDescription = menuOptions.title,
                modifier = Modifier.padding(top = 4.dp).size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = menuOptions.title,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = menuOptions.description,
                    fontFamily = Poppins,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = Color(0xFF9A9A9A).copy(alpha = 0.73f),
                    fontWeight = FontWeight.Normal,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(Res.drawable.arrow_right),
                contentDescription = "navigate",
                modifier = Modifier.padding(top = 6.dp).size(16.dp)
            )
        }
    }
}

@Composable
fun ProfileMenuItemWithToggle(menuOptions: MenuOptions){

    var checked by remember{mutableStateOf(false)}

    Box(Modifier.fillMaxWidth().clickable { menuOptions.onClick()
            checked=!checked}){
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp, horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(menuOptions.icon),
                tint = appBlue,
                contentDescription = menuOptions.title,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = menuOptions.title,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = menuOptions.description,
                    fontFamily = Poppins,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = Color(0xFF9A9A9A).copy(alpha = 0.73f),
                    fontWeight = FontWeight.Normal,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            CustomSwitch(
                checked = checked,
                onCheckedChange = {checked=it}
            )
        }
    }
}

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    val width = 34.dp
    val height = 20.dp
    val thumbSize = 16.dp

    val checkedTrackColor = Color(0xffEFF4FF)
    val uncheckedTrackColor = Color(0xffD9D9D9)
    val uncheckedThumbColor = Color.White
    val checkedThumbColor = appBlue

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) width - thumbSize - 2.5.dp else 2.5.dp,
        label = "thumbOffset"
    )

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(CircleShape)
            .background(if (checked) checkedTrackColor else uncheckedTrackColor)
            .border(0.5.dp, uncheckedTrackColor, CircleShape)
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(thumbSize)
                .offset(x = thumbOffset)
                .clip(CircleShape)
                .background(if (checked) checkedThumbColor else uncheckedThumbColor)
        )
    }
}


@Composable
fun ProfileCard(img:String, name:String, phone:String){
    Box(modifier=Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(appBlue),
        contentAlignment = Alignment.Center){
        Row(modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = img,
                contentDescription = "profileImage",
                modifier = Modifier.size(72.dp).clip(CircleShape).border(width = 2.dp, color = Color.White, shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    lineHeight = 22.sp,
                    color = Color.White,
                    maxLines = 1
                )
                Text(
                    text = phone,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun TopBar(text:String, onBackClick:()-> Unit){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Text(
            text = text,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            style = TextStyle(letterSpacing = (-0.4).sp),
            textAlign = TextAlign.Center
        )

        Surface(modifier = Modifier
            .align(Alignment.CenterStart)
            .size(48.dp),
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Box(modifier=Modifier.fillMaxSize()
                .clickable(onClick = {
                   onBackClick()
                }),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(Res.drawable.back_arrow),
                    contentDescription = "back",
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

data class MenuOptions(
    val title:String,
    val description:String,
    val icon: DrawableResource,
    val onClick: () -> Unit
)