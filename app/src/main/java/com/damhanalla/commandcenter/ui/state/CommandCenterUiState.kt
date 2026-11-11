package com.damhanalla.commandcenter.ui.state

import com.damhanalla.commandcenter.domain.model.ClientProfile
import com.damhanalla.commandcenter.domain.model.DocumentRecord
import com.damhanalla.commandcenter.domain.model.GrantApplication
import com.damhanalla.commandcenter.domain.model.GrantOpportunity
import com.damhanalla.commandcenter.domain.model.TaskItem

sealed interface CommandCenterUiState {
    data object Loading : CommandCenterUiState
    data class Empty(val message: String) : CommandCenterUiState
    data class Error(val cause: String) : CommandCenterUiState
    data class Success(
        val upcomingDeadlines: List<GrantApplication>,
        val activeClients: List<ClientProfile>,
        val prioritizedTasks: List<TaskItem>,
        val expiringDocuments: List<DocumentRecord>,
        val catalogOpportunities: List<GrantOpportunity>
    ) : CommandCenterUiState
}
