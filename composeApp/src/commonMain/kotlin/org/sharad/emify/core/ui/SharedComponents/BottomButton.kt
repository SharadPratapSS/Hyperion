package org.sharad.emify.core.ui.SharedComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sharad.emify.core.ui.theme.Poppins

@Composable
fun BottomButton(text: String, onClick: () -> Unit, enabled: Boolean=true,showLoader:Boolean=false, modifier:Modifier=Modifier.padding(horizontal = 20.dp)) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier=modifier.fillMaxWidth().height(56.dp),
        shape = RoundedCornerShape(15.dp),
    ){
        Text(text = text,
            fontSize = 16.sp,
            fontFamily = Poppins,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        AnimatedVisibility(showLoader){
            CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White, strokeWidth = 1.5.dp)
        }
    }
}
