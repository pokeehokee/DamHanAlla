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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.damhanalla.commandcenter.domain.model.GrantOpportunity
import com.damhanalla.commandcenter.ui.components.InfoTooltipBubble
import com.damhanalla.commandcenter.ui.theme.BorderDark
import com.damhanalla.commandcenter.ui.theme.PrimaryEmerald
import com.damhanalla.commandcenter.ui.theme.SurfaceDark
import com.damhanalla.commandcenter.ui.theme.TextPrimary
import com.damhanalla.commandcenter.ui.theme.TextSecondary

@Composable
fun GrantsScreen(
    opportunities: List<GrantOpportunity>,
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
                text = "Grant Catalog & Programs",
                style = MaterialTheme.typography.headlineMedium.copy(color = TextPrimary)
            )
            Text(
                text = "Active funding opportunities and matching criteria",
                style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
            )
            Spacer(modifier = Modifier.height(12.dp))
            InfoTooltipBubble(
                title = "Cost-Share & Match Rules",
                explanation = "Federal programs like USDA REAP require a minimum 50% non-federal matching commitment from client cash or commercial bank financing."
            )
        }

        items(opportunities) { opp ->
            OpportunityCard(opportunity = opp)
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun OpportunityCard(opportunity: GrantOpportunity) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BorderDark, RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = opportunity.agency,
                style = MaterialTheme.typography.labelMedium.copy(color = PrimaryEmerald)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = opportunity.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Ceiling: ${opportunity.formattedCeiling}",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
                Text(
                    text = opportunity.formattedMatch,
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = opportunity.summary,
                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
            )
        }
    }
}
