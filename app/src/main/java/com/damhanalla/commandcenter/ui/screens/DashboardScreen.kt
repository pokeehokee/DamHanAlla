package com.damhanalla.commandcenter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.Warning
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
import androidx.compose.ui.unit.sp
import com.damhanalla.commandcenter.domain.model.GrantApplication
import com.damhanalla.commandcenter.domain.model.TaskItem
import com.damhanalla.commandcenter.ui.components.InfoTooltipBubble
import com.damhanalla.commandcenter.ui.state.CommandCenterUiState
import com.damhanalla.commandcenter.ui.theme.AccentAmber
import com.damhanalla.commandcenter.ui.theme.BorderDark
import com.damhanalla.commandcenter.ui.theme.PrimaryEmerald
import com.damhanalla.commandcenter.ui.theme.SurfaceDark
import com.damhanalla.commandcenter.ui.theme.SurfaceVariantDark
import com.damhanalla.commandcenter.ui.theme.TextPrimary
import com.damhanalla.commandcenter.ui.theme.TextSecondary

@Composable
fun DashboardScreen(
    state: CommandCenterUiState.Success,
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
                text = "DamHanAlla Command Center",
                style = MaterialTheme.typography.headlineMedium.copy(color = TextPrimary)
            )
            Text(
                text = "Operations, Compliance & Grant Pipeline",
                style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
            )
            Spacer(modifier = Modifier.height(12.dp))

            InfoTooltipBubble(
                title = "Uniform Guidance 2 CFR 200 Reminder",
                explanation = "Grant success bonuses must be paid from client unrestricted operating funds, NEVER deducted from federal grant award disbursements."
            )
        }

        item {
            MetricBanner(state = state)
        }

        item {
            Text(
                text = "Critical Deadlines (Next 45 Days)",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
        }

        items(state.upcomingDeadlines) { application ->
            DeadlineCard(application = application)
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Actionable Tasks",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                )
                InfoTooltipBubble(
                    title = "Task Prioritization",
                    explanation = "Tasks are auto-sorted by due date and blocker status to maintain audit timelines."
                )
            }
        }

        items(state.prioritizedTasks) { task ->
            TaskCard(task = task, onComplete = { onCompleteTask(task.id) })
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun MetricBanner(state: CommandCenterUiState.Success) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        MetricCard(
            label = "Active Clients",
            value = state.activeClients.size.toString(),
            modifier = Modifier.weight(1f)
        )
        MetricCard(
            label = "Open Pipeline",
            value = state.upcomingDeadlines.size.toString(),
            modifier = Modifier.weight(1f)
        )
        MetricCard(
            label = "Pending Tasks",
            value = state.prioritizedTasks.size.toString(),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun MetricCard(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BorderDark, RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = PrimaryEmerald
                )
            )
        }
    }
}

@Composable
private fun DeadlineCard(application: GrantApplication) {
    val daysLeft = application.daysRemaining(System.currentTimeMillis())
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BorderDark, RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = application.currentStage.displayName,
                    style = MaterialTheme.typography.labelMedium.copy(color = PrimaryEmerald)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (daysLeft <= 14) AccentAmber.copy(alpha = 0.2f) else SurfaceVariantDark)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "$daysLeft Days Left",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = if (daysLeft <= 14) AccentAmber else TextSecondary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Requested: $%,d".format(application.requestedAmountCents / 100),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = application.internalNotes,
                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
            )
        }
    }
}

@Composable
private fun TaskCard(task: TaskItem, onComplete: () -> Unit) {
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
            IconButton(onClick = onComplete) {
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
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary
                    )
                )
                Text(
                    text = task.detailedInstructions,
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary),
                    maxLines = 2
                )
            }
        }
    }
}
