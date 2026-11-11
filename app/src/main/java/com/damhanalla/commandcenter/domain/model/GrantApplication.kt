package com.damhanalla.commandcenter.domain.model

data class GrantApplication(
    val id: String,
    val clientId: String,
    val grantOpportunityId: String,
    val requestedAmountCents: Long,
    val clientMatchCommittedCents: Long,
    val currentStage: ApplicationStage,
    val loiDeadlineEpochMs: Long?,
    val finalDeadlineEpochMs: Long,
    val anticipatedAwardDateEpochMs: Long?,
    val isSamGovUeiVerified: Boolean,
    val isBudgetWorkbookFinalized: Boolean,
    val submissionConfirmationNumber: String?,
    val internalNotes: String
) {
    fun daysRemaining(currentEpochMs: Long): Long {
        val diff = finalDeadlineEpochMs - currentEpochMs
        return if (diff > 0) diff / (1000 * 60 * 60 * 24) else 0L
    }
}
