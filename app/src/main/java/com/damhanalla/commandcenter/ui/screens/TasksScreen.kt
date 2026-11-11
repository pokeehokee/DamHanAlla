package com.damhanalla.commandcenter.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.damhanalla.commandcenter.domain.model.TaskItem
import com.damhanalla.commandcenter.ui.components.InfoTooltipBubble
import com.damhanalla.commandcenter.ui.theme.BorderDark
import com.damhanalla.commandcenter.ui.theme.PrimaryEmerald
import com.damhanalla.commandcenter.ui.theme.SurfaceDark
import com.damhanalla.commandcenter.ui.theme.TextPrimary
import com.damhanalla.commandcenter.ui.theme.TextSecondary

@Composable
fun TasksScreen(
    tasks: List<TaskItem>,
    onCompleteTask: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Operational Task Queue",
                style = MaterialTheme.typography.headlineMedium.copy(color = TextPrimary)
            )
            Text(
                text = "Due diligence, audit filing checkpoints, and client document requests",
                style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
            )
            Spacer(modifier = Modifier.height(12.dp))
            InfoTooltipBubble(
                title = "Client Blocker Protocol",
                explanation = "If a client exceeds 14 days without delivering tax returns, invoke your MSA pause clause to reset grant deadline commitments."
            )
        }

        items(tasks) { task ->
            Card(
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, BorderDark, RoundedCornerShape(12.dp))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    IconButton(onClick = { onCompleteTask(task.id) }) {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircleOutline,
                            contentDescription = "Complete task",
                            tint = PrimaryEmerald
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                        )
                        Text(
                            text = task.detailedInstructions,
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
