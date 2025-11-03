package org.sharad.emify.core.ui.SharedComponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DashedDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray,
    thickness: Dp = 1.dp,
    dashWidth: Float = 12f,
    gapWidth: Float = 8f
) {
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(thickness)) {
        val y = size.height / 2
        drawLine(
            color = color,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = thickness.toPx(),
            cap = StrokeCap.Butt,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, gapWidth), 0f)
        )
    }
}
