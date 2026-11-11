package com.damhanalla.commandcenter.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.damhanalla.commandcenter.ui.theme.BorderDark
import com.damhanalla.commandcenter.ui.theme.PrimaryEmerald
import com.damhanalla.commandcenter.ui.theme.SurfaceVariantDark
import com.damhanalla.commandcenter.ui.theme.TextPrimary
import com.damhanalla.commandcenter.ui.theme.TextSecondary

@Composable
fun InfoTooltipBubble(
    title: String,
    explanation: String,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val haptic = LocalHapticFeedback.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(SurfaceVariantDark)
                    .border(1.dp, if (isExpanded) PrimaryEmerald else BorderDark, CircleShape)
                    .clickable {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        isExpanded = !isExpanded
                    }
            ) {
                Icon(
                    imageVector = Icons.Outlined.HelpOutline,
                    contentDescription = "Explain $title",
                    tint = if (isExpanded) PrimaryEmerald else TextSecondary,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 36.dp, top = 4.dp, bottom = 8.dp, end = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceVariantDark)
                    .border(1.dp, BorderDark, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = explanation,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        lineHeight = 18.sp
                    )
                )
            }
        }
    }
}
