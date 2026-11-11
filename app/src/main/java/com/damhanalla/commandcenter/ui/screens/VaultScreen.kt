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
import com.damhanalla.commandcenter.domain.model.DocumentRecord
import com.damhanalla.commandcenter.ui.components.InfoTooltipBubble
import com.damhanalla.commandcenter.ui.theme.BorderDark
import com.damhanalla.commandcenter.ui.theme.PrimaryEmerald
import com.damhanalla.commandcenter.ui.theme.SurfaceDark
import com.damhanalla.commandcenter.ui.theme.TextPrimary
import com.damhanalla.commandcenter.ui.theme.TextSecondary

@Composable
fun VaultScreen(
    documents: List<DocumentRecord>,
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
                text = "Document & Contract Vault",
                style = MaterialTheme.typography.headlineMedium.copy(color = TextPrimary)
            )
            Text(
                text = "Signed NDAs, tax returns, and official agency submission receipts",
                style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
            )
            Spacer(modifier = Modifier.height(12.dp))
            InfoTooltipBubble(
                title = "Expiration Monitoring",
                explanation = "State filings and SAM.gov annual renewals trigger warnings 30 days prior to lapse to prevent grant disqualification."
            )
        }

        items(documents) { doc ->
            Card(
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, BorderDark, RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = doc.category.displayName,
                        style = MaterialTheme.typography.labelMedium.copy(color = PrimaryEmerald)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = doc.fileName,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "URI: ${doc.localUri}",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
