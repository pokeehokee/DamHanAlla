package com.damhanalla.commandcenter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damhanalla.commandcenter.domain.model.ApplicationStage
import com.damhanalla.commandcenter.domain.model.ClientProfile
import com.damhanalla.commandcenter.domain.model.DocumentCategory
import com.damhanalla.commandcenter.domain.model.DocumentRecord
import com.damhanalla.commandcenter.domain.model.EntityStructure
import com.damhanalla.commandcenter.domain.model.GrantApplication
import com.damhanalla.commandcenter.domain.model.GrantOpportunity
import com.damhanalla.commandcenter.domain.model.ServiceTier
import com.damhanalla.commandcenter.domain.model.TaskItem
import com.damhanalla.commandcenter.domain.model.TaskPriority
import com.damhanalla.commandcenter.domain.model.TaskStatus
import com.damhanalla.commandcenter.domain.repository.ClientRepository
import com.damhanalla.commandcenter.domain.repository.DocumentRepository
import com.damhanalla.commandcenter.domain.repository.GrantRepository
import com.damhanalla.commandcenter.domain.repository.TaskRepository
import com.damhanalla.commandcenter.ui.state.CommandCenterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommandCenterViewModel @Inject constructor(
    private val grantRepository: GrantRepository,
    private val clientRepository: ClientRepository,
    private val taskRepository: TaskRepository,
    private val documentRepository: DocumentRepository
) : ViewModel() {

    init {
        seedInitialDemoData()
    }

    val uiState: StateFlow<CommandCenterUiState> = combine(
        grantRepository.getUpcomingDeadlines(windowDays = 45),
        clientRepository.getClients(),
        taskRepository.getActiveTasks(),
        documentRepository.getExpiringDocuments(windowDays = 60),
        grantRepository.getOpportunities()
    ) { deadlines, clients, tasks, docs, opportunities ->
        if (deadlines.isEmpty() && clients.isEmpty() && tasks.isEmpty() && opportunities.isEmpty()) {
            CommandCenterUiState.Empty("No operations recorded yet. Add a client or grant to start.")
        } else {
            CommandCenterUiState.Success(
                upcomingDeadlines = deadlines,
                activeClients = clients,
                prioritizedTasks = tasks,
                expiringDocuments = docs,
                catalogOpportunities = opportunities
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CommandCenterUiState.Loading
    )

    fun markTaskComplete(id: String) {
        viewModelScope.launch {
            taskRepository.completeTask(id)
        }
    }

    private fun seedInitialDemoData() {
        viewModelScope.launch {
            val opp = GrantOpportunity(
                id = "reap-2026-clean-energy",
                title = "USDA REAP Renewable Energy Systems Grant",
                agency = "USDA Rural Development",
                assistanceListingNumber = "10.868",
                fundingCeilingCents = 100000000L, // $1M max
                fundingFloorCents = 250000L,     // $2.5k min
                matchRequirementPercentage = 0.50,
                eligibleEntityTypes = listOf(EntityStructure.LLC, EntityStructure.COOPERATIVE, EntityStructure.SOLE_PROPRIETORSHIP),
                targetNaicsCodes = listOf("1111", "1121", "4451"),
                closingDateEpochMs = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000),
                solicitationUrl = "https://www.rd.usda.gov/reap",
                summary = "Guaranteed 50% capital cost recovery for grain dryers, commercial solar, and refrigeration upgrades."
            )
            grantRepository.saveOpportunity(opp)

            val client = ClientProfile(
                id = "client-benton-mill",
                legalBusinessName = "Benton Grain & Millworks LLC",
                dbaName = "Warsaw Feed Supply",
                structure = EntityStructure.LLC,
                primaryNaics = "1111",
                federalEin = "43-1234567",
                samGovUei = "SAM99283KLA9",
                cageCode = "8Z91K",
                physicalCity = "Warsaw",
                physicalState = "MO",
                primaryContactName = "John Miller",
                primaryContactEmail = "jmiller@bentonmillworks.com",
                primaryContactPhone = "(660) 438-0000",
                annualGrossRevenueCents = 85000000L,
                employeeCount = 8,
                activeServiceTier = ServiceTier.HYBRID_PACKAGING,
                agreedSuccessBonusPercentage = 0.06,
                initialAuditFeePaid = true,
                isNdSigned = true,
                onboardingDateEpochMs = System.currentTimeMillis()
            )
            clientRepository.saveClient(client)

            val app = GrantApplication(
                id = "app-reap-benton-01",
                clientId = client.id,
                grantOpportunityId = opp.id,
                requestedAmountCents = 12500000L, // $125k grant
                clientMatchCommittedCents = 12500000L,
                currentStage = ApplicationStage.DRAFTING_NARRATIVE,
                loiDeadlineEpochMs = null,
                finalDeadlineEpochMs = System.currentTimeMillis() + (18L * 24 * 60 * 60 * 1000),
                anticipatedAwardDateEpochMs = System.currentTimeMillis() + (90L * 24 * 60 * 60 * 1000),
                isSamGovUeiVerified = true,
                isBudgetWorkbookFinalized = false,
                submissionConfirmationNumber = null,
                internalNotes = "Utility meter consumption history verified. Waiting on final contractor solar estimate."
            )
            grantRepository.saveApplication(app)

            val task = TaskItem(
                id = "task-01",
                title = "Collect 2024 Form 1120-S Schedule K-1s",
                detailedInstructions = "Verify member ownership percentages match SAM.gov entity disclosure for John Miller.",
                priority = TaskPriority.URGENT_HIGH,
                status = TaskStatus.IN_PROGRESS,
                dueDateEpochMs = System.currentTimeMillis() + (3L * 24 * 60 * 60 * 1000),
                estimatedMinutes = 45,
                linkedClientId = client.id,
                linkedApplicationId = app.id,
                blockerExplanation = null
            )
            taskRepository.saveTask(task)

            val doc = DocumentRecord(
                id = "doc-01",
                fileName = "Benton_Grain_SAM_Registration_2026.pdf",
                localUri = "file:///vault/benton_grain_sam.pdf",
                category = DocumentCategory.LEGAL_GOVERNANCE,
                linkedClientId = client.id,
                linkedApplicationId = app.id,
                expirationDateEpochMs = System.currentTimeMillis() + (22L * 24 * 60 * 60 * 1000),
                uploadedAtEpochMs = System.currentTimeMillis(),
                fileSizeBytes = 412980L
            )
            documentRepository.saveDocument(doc)
        }
    }
}
