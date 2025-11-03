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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.sharad.emify.core.ui.theme.Poppins

@Composable
fun PhoneNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String? = null,
    enable: Boolean = true,
    icon: DrawableResource? = null,
    iconClick: (() -> Unit)? = null,
    showError: Boolean? = null
) {
    val prefix = "+91 "

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            label?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 2.dp),
                    fontFamily = Poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
            }

            showError?.let {
                if (it) {
                    Text(
                        text = "Invalid Phone Number",
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Red
                    )
                }
            }
        }

        BasicTextField(
            enabled = enable,
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= 10) {
                    onValueChange(newValue)
                }
            },
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = Poppins,
                color = Color.Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ), // always number keyboard
            modifier = Modifier
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
                    } else Modifier
                ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = prefix,
                                    fontFamily = Poppins,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            Box(modifier = Modifier.weight(1f)){
                                if (value.isEmpty()) {
                                    Text(
                                        text = placeholder,
                                        fontFamily = Poppins,
                                        fontSize = 16.sp,
                                        maxLines = 1,
                                        color = Color(0xFFB5B5B5)
                                    )
                                }
                                innerTextField()
                            }
                        }
                    }
                    icon?.let { it ->
                        Image(
                            painter = painterResource(it),
                            contentDescription = "icon",
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(16.dp)
                        )
                    }
                }
            }
        )
    }
}
