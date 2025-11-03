package org.sharad.emify.core.ui.SharedComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.sharad.emify.core.ui.theme.Poppins

@Composable
fun IconTextField(value: String,
                 onValueChange: (String) -> Unit,
                 placeholder: String,
                 label: String?=null,
                 enable:Boolean=true,
                 icon: DrawableResource,
                 iconClick: (() -> Unit)?=null,
                 keyboardOptions: KeyboardOptions?=null,
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
            keyboardOptions = keyboardOptions?: KeyboardOptions.Default,
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
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color(0xFFB5B5B5)
                            )
                        }
                        innerTextField()
                    }
                    Image(
                        painter = painterResource(icon),
                        contentDescription = "icon",
                        modifier = Modifier.padding(horizontal = 16.dp).size(20.dp),
                    )
                }
            }
        )
    }
}