package org.sharad.emify.features.home.presentation.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.back_arrow
import emify.composeapp.generated.resources.info_icon
import emify.composeapp.generated.resources.upi_footer
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sharad.emify.core.ui.SharedComponents.MandateItemFailed
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.core.ui.theme.f7Gray

@Composable
@Preview()
fun ProfileDrawer(){
    val tempImg="https://img.freepik.com/premium-vector/man-professional-business-casual-young-avatar-icon-illustration_1277826-623.jpg"
    Box(modifier= Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp),
        contentAlignment = Alignment.Center){
        Column(modifier=Modifier.fillMaxHeight().padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {
            TopBar("My Profile", {})
            ProfileCard(tempImg,"Sharad Singh", "9198663022")
            ProfileOptionList(modifier=Modifier.weight(1f))
            Image(painter = painterResource(Res.drawable.upi_footer), contentDescription = "footer", modifier = Modifier.padding(bottom = 12.dp))
        }
    }
}

@Composable
fun ProfileOptionList(modifier: Modifier){
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

        }
    }
}

@Composable
fun Footer(){

}

@Composable
fun ProfileCard(img:String, name:String, phone:String){
    Box(modifier=Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(appBlue),
        contentAlignment = Alignment.Center){
        Row(modifier = Modifier.fillMaxWidth().padding(28.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = img,
                contentDescription = "profileImage",
                modifier = Modifier.size(80.dp).clip(CircleShape).border(width = 2.dp, color = Color.White, shape = CircleShape),
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