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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import emify.composeapp.generated.resources.Res
import emify.composeapp.generated.resources.loan_banner
import emify.composeapp.generated.resources.notification_icon
import emify.composeapp.generated.resources.profile_icon
import emify.composeapp.generated.resources.retail_banner
import emify.composeapp.generated.resources.sub_banner
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.sharad.emify.core.navigation.Routes_ProfileMenu
import org.sharad.emify.core.ui.SharedComponents.BottomButton
import org.sharad.emify.core.ui.SharedComponents.MandateItemFailed
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.appBlue
import org.sharad.emify.core.ui.theme.f7Gray
import org.sharad.emify.core.ui.theme.fadeGreen
import org.sharad.emify.core.ui.theme.fadeOrange
import org.sharad.emify.core.ui.theme.fadeYellow
import org.sharad.emify.core.ui.theme.grayBorder
import org.sharad.emify.core.ui.theme.headGreen
import org.sharad.emify.core.ui.theme.headOrange
import org.sharad.emify.core.ui.theme.headYellow

@Composable
fun HomeScreen(navController: NavHostController) {

    val pagerState=rememberPagerState(
        0,
        pageCount = { 3 }
    )

    val name= "Sharad"
    Column(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.weight(1f).padding( end = 20.dp, start=20.dp, top = 24.dp),
        ) {

            ProfileBar(name = name, navController=navController)

            Spacer(modifier = Modifier.height(24.dp))

            ScrollBanner(pagerState)

            Spacer(modifier = Modifier.height(16.dp))

            Scroller(pagerState = pagerState)

            Spacer(modifier = Modifier.height(20.dp))

            MandateList()

            Spacer(modifier = Modifier.height(8.dp))

        }
        Box(modifier = Modifier.padding(vertical = 8.dp)){
            BottomButton("+ Create New Mandate", onClick = {},true)
        }
    }
}

@Composable
fun MandateList() {
    val mandateList= Mandate("Sharad", "This is a sample mandate", "0", "10,000", "Expired")
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)){
        Text(text = "Recent Mandate", style = MaterialTheme.typography.headlineMedium)
        Box()
        {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(4) {
                        MandateItemFailed(mandate = mandateList)
                        if (it < 3) {
                            HorizontalDivider(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                                color = f7Gray,
                                thickness = 2.dp
                            )
                        }
                        else{
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }

            Box(modifier = Modifier.align(Alignment.BottomCenter)
                .width(140.dp)
                .height(44.dp)
                .offset(y = (22).dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .border(width = 2.dp, color = grayBorder, shape = RoundedCornerShape(50))
            ){
                Text(
                    "View More",
                    fontSize = 12.sp,
                    fontFamily = Poppins,
                    color = Color(0xffB5B5B5),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun Scroller(pagerState: PagerState) {
    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(pagerState.pageCount) {
                Box(
                    modifier = Modifier.height(10.dp)
                        .clip(RoundedCornerShape(50))
                        .width(if (pagerState.currentPage == it) 30.dp else 10.dp)
                        .background(if (pagerState.currentPage == it) appBlue else Color(0xffD1D1D1))
                )
            }
        }
    }
}

@Composable
fun ProfileBar(name: String, navController: NavHostController) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier = Modifier
                .size(56.dp),
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .clickable(onClick = {
                        navController.navigate(Routes_ProfileMenu)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.profile_icon),
                    contentDescription = "profile",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Text("Hi ${name}",
            fontSize = 20.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
            maxLines = 1
        )

        Surface(
            modifier = Modifier
                .size(56.dp),
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .clickable(onClick = {
                    }),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.notification_icon),
                    contentDescription = "profile",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun ScrollBanner(pagerState: PagerState){
    val bannerList= listOf<Banner>(
        Banner("Retail Collections", "Shopkeepers collect payments instantly—no tracking, no follow-ups.", Res.drawable.retail_banner, fadeOrange,
            headOrange
        ),
        Banner("Subscription Collections", "Automate and collect recurring fees—on time, every time.", Res.drawable.sub_banner, fadeYellow, headYellow),
        Banner("Loan Collection", "Easily collect EMIs for loans—timely, tracked, and automated.", Res.drawable.loan_banner, fadeGreen,
            headGreen
        ))

    HorizontalPager(
        state = pagerState,
        pageSpacing = 16.dp
    ){
        when(it){
            0->BannerItem(banner = bannerList[0])
            1->BannerItem(banner = bannerList[1])
            2->BannerItem(banner = bannerList[2])
        }
    }
}

@Composable
fun BannerItem(banner: Banner){
    Surface(
        modifier = Modifier.padding(2.dp),
        shape = RoundedCornerShape(20.dp),
        color = banner.backgroundColor,
        shadowElevation = 2.dp){
        Row(
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .height(132.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
                Text(
                    banner.title,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    color = banner.headColor
                )
                Text(
                    banner.content,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )
            }

            Image(
                painter = painterResource(banner.image),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight().padding(top = 14.dp),
                contentScale = ContentScale.FillHeight
            )

        }
    }
}

data class Banner(
    val title: String,
    val content: String,
    val image: DrawableResource,
    val backgroundColor: Color,
    val headColor: Color
)

data class Mandate(
    val name: String,
    val content: String,
    val paid: String,
    val unpaid: String,
    val status: String
)