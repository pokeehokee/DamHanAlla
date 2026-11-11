package com.damhanalla.commandcenter.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.damhanalla.commandcenter.domain.model.ApplicationStage
import com.damhanalla.commandcenter.domain.model.GrantApplication

@Entity(
    tableName = "grant_applications",
    foreignKeys = [
        ForeignKey(
            entity = ClientProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["clientId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GrantOpportunityEntity::class,
            parentColumns = ["id"],
            childColumns = ["grantOpportunityId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("clientId"), Index("grantOpportunityId")]
)
data class GrantApplicationEntity(
    @PrimaryKey val id: String,
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
    fun toDomainModel(): GrantApplication = GrantApplication(
        id = id,
        clientId = clientId,
        grantOpportunityId = grantOpportunityId,
        requestedAmountCents = requestedAmountCents,
        clientMatchCommittedCents = clientMatchCommittedCents,
        currentStage = currentStage,
        loiDeadlineEpochMs = loiDeadlineEpochMs,
        finalDeadlineEpochMs = finalDeadlineEpochMs,
        anticipatedAwardDateEpochMs = anticipatedAwardDateEpochMs,
        isSamGovUeiVerified = isSamGovUeiVerified,
        isBudgetWorkbookFinalized = isBudgetWorkbookFinalized,
        submissionConfirmationNumber = submissionConfirmationNumber,
        internalNotes = internalNotes
    )

    companion object {
        fun fromDomainModel(domain: GrantApplication): GrantApplicationEntity = GrantApplicationEntity(
            id = domain.id,
            clientId = domain.clientId,
            grantOpportunityId = domain.grantOpportunityId,
            requestedAmountCents = domain.requestedAmountCents,
            clientMatchCommittedCents = domain.clientMatchCommittedCents,
            currentStage = domain.currentStage,
            loiDeadlineEpochMs = domain.loiDeadlineEpochMs,
            finalDeadlineEpochMs = domain.finalDeadlineEpochMs,
            anticipatedAwardDateEpochMs = domain.anticipatedAwardDateEpochMs,
            isSamGovUeiVerified = domain.isSamGovUeiVerified,
            isBudgetWorkbookFinalized = domain.isBudgetWorkbookFinalized,
            submissionConfirmationNumber = domain.submissionConfirmationNumber,
            internalNotes = domain.internalNotes
        )
    }
}
