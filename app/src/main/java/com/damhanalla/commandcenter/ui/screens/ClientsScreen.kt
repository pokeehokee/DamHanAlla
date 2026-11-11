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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.damhanalla.commandcenter.domain.model.ClientProfile
import com.damhanalla.commandcenter.ui.components.InfoTooltipBubble
import com.damhanalla.commandcenter.ui.theme.BorderDark
import com.damhanalla.commandcenter.ui.theme.PrimaryEmerald
import com.damhanalla.commandcenter.ui.theme.SurfaceDark
import com.damhanalla.commandcenter.ui.theme.TextPrimary
import com.damhanalla.commandcenter.ui.theme.TextSecondary

@Composable
fun ClientsScreen(
    clients: List<ClientProfile>,
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
                text = "Client Relationship Directory",
                style = MaterialTheme.typography.headlineMedium.copy(color = TextPrimary)
            )
            Text(
                text = "Business entity registrations, UEI numbers, and billing tiers",
                style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
            )
            Spacer(modifier = Modifier.height(12.dp))
            InfoTooltipBubble(
                title = "SAM.gov Unique Entity Identifier (UEI)",
                explanation = "A 12-character alphanumeric code required by all federal granting agencies. Replaced DUNS numbers in 2022."
            )
        }

        items(clients) { client ->
            ClientCard(client = client)
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun ClientCard(client: ClientProfile) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, BorderDark, RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = client.legalBusinessName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
            if (client.dbaName != null) {
                Text(
                    text = "DBA: ${client.dbaName}",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Structure: ${client.structure.displayName}",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
                Text(
                    text = "Location: ${client.physicalCity}, ${client.physicalState}",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "SAM UEI: ${client.samGovUei ?: "None"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = PrimaryEmerald)
                )
                Text(
                    text = "Tier: ${client.activeServiceTier.displayName}",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
            }
        }
    }
}
