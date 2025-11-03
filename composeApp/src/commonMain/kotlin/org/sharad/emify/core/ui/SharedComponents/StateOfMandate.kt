package org.sharad.emify.core.ui.SharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sharad.emify.core.domain.Mandate.MandateItem
import org.sharad.emify.core.ui.theme.Poppins
import org.sharad.emify.core.ui.theme.f7Gray

@Composable
fun MandateItemExpired(mandate: MandateItem, onClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)
        .then(onClick?.let { Modifier.clickable(onClick = it) } ?: Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(f7Gray),
            contentAlignment = Alignment.Center) {
            Text(
                text = mandate.name[0].toString(),
                fontSize = 22.sp,
                fontFamily = Poppins,
            )
        }
        Column(modifier=Modifier.weight(1f)) {
            Text(
                text=mandate.name,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text=mandate.content,
                color = Color(0xff9A9A9ABB).copy(alpha = 0.73f),
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontFamily = Poppins,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text="₹${mandate.paid}/₹${mandate.unpaid}",
                lineHeight = 14.sp,
                style = MaterialTheme.typography.bodySmall
            )
            Box(modifier = Modifier.clip(RoundedCornerShape(50)).background(Color(0xffD9D9D9))){
                Text(mandate.status.name,
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Center).padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}
@Composable
fun MandateItemActive(mandate: MandateItem, onClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)
        .then(onClick?.let { Modifier.clickable(onClick = it) } ?: Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(f7Gray),
            contentAlignment = Alignment.Center) {
            Text(
                text = mandate.name[0].toString(),
                fontSize = 22.sp,
                fontFamily = Poppins,
            )
        }
        Column(modifier=Modifier.weight(1f)) {
            Text(
                text=mandate.name,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text=mandate.content,
                color = Color(0xff9A9A9ABB).copy(alpha = 0.73f),
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontFamily = Poppins,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text="₹${mandate.paid}/₹${mandate.unpaid}",
                lineHeight = 14.sp,
                style = MaterialTheme.typography.bodySmall
            )
            Box(modifier = Modifier.clip(RoundedCornerShape(50)).background(Color(0xffDDFFDF))){
                Text(mandate.status.name,
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff1FAF38),
                    modifier = Modifier.align(Alignment.Center).padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}
@Composable
fun MandateItemPending(mandate: MandateItem, onClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)
        .then(onClick?.let { Modifier.clickable(onClick = it) } ?: Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(f7Gray),
            contentAlignment = Alignment.Center) {
            Text(
                text = mandate.name[0].toString(),
                fontSize = 22.sp,
                fontFamily = Poppins,
            )
        }
        Column(modifier=Modifier.weight(1f)) {
            Text(
                text=mandate.name,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text=mandate.content,
                color = Color(0xff9A9A9ABB).copy(alpha = 0.73f),
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontFamily = Poppins,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text="₹${mandate.paid}/₹${mandate.unpaid}",
                lineHeight = 14.sp,
                style = MaterialTheme.typography.bodySmall
            )
            Box(modifier = Modifier.clip(RoundedCornerShape(50)).background(Color(0xffFFE69C))){
                Text(mandate.status.name,
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xffD7A000),
                    modifier = Modifier.align(Alignment.Center).padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}
@Composable
fun MandateItemFinished(mandate: MandateItem, onClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)
        .then(onClick?.let { Modifier.clickable(onClick = it) } ?: Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(f7Gray),
            contentAlignment = Alignment.Center) {
            Text(
                text = mandate.name[0].toString(),
                fontSize = 22.sp,
                fontFamily = Poppins,
            )
        }
        Column(modifier=Modifier.weight(1f)) {
            Text(
                text=mandate.name,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text=mandate.content,
                color = Color(0xff9A9A9ABB).copy(alpha = 0.73f),
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontFamily = Poppins,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text="₹${mandate.paid}/₹${mandate.unpaid}",
                lineHeight = 14.sp,
                style = MaterialTheme.typography.bodySmall
            )
            Box(modifier = Modifier.clip(RoundedCornerShape(50)).background(Color(0xffDDFFDF))){
                Text(mandate.status.name,
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff1FAF38),
                    modifier = Modifier.align(Alignment.Center).padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun MandateItemFailed(mandate: MandateItem, onClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)
        .then(onClick?.let { Modifier.clickable(onClick = it) } ?: Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(f7Gray),
            contentAlignment = Alignment.Center) {
            Text(
                text = mandate.name[0].toString(),
                fontSize = 22.sp,
                fontFamily = Poppins,
            )
        }
        Column(modifier=Modifier.weight(1f)) {
            Text(
                text=mandate.name,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text=mandate.content,
                color = Color(0xff9A9A9ABB).copy(alpha = 0.73f),
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontFamily = Poppins,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text="₹${mandate.paid}/₹${mandate.unpaid}",
                lineHeight = 14.sp,
                style = MaterialTheme.typography.bodySmall
            )
            Box(modifier = Modifier.clip(RoundedCornerShape(50)).background(Color(0xffFFC0C0))){
                Text(mandate.status.name,
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    fontFamily = Poppins,
                    color = Color(0xffFF0000),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Center).padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}