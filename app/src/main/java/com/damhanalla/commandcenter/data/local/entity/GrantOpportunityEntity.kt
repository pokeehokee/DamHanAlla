package com.damhanalla.commandcenter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.damhanalla.commandcenter.domain.model.EntityStructure
import com.damhanalla.commandcenter.domain.model.GrantOpportunity

@Entity(tableName = "grant_opportunities")
data class GrantOpportunityEntity(
    @PrimaryKey val id: String,
    val title: String,
    val agency: String,
    val assistanceListingNumber: String,
    val fundingCeilingCents: Long,
    val fundingFloorCents: Long,
    val matchRequirementPercentage: Double,
    val eligibleEntityTypes: List<EntityStructure>,
    val targetNaicsCodes: List<String>,
    val closingDateEpochMs: Long,
    val solicitationUrl: String,
    val summary: String
) {
    fun toDomainModel(): GrantOpportunity = GrantOpportunity(
        id = id,
        title = title,
        agency = agency,
        assistanceListingNumber = assistanceListingNumber,
        fundingCeilingCents = fundingCeilingCents,
        fundingFloorCents = fundingFloorCents,
        matchRequirementPercentage = matchRequirementPercentage,
        eligibleEntityTypes = eligibleEntityTypes,
        targetNaicsCodes = targetNaicsCodes,
        closingDateEpochMs = closingDateEpochMs,
        solicitationUrl = solicitationUrl,
        summary = summary
    )

    companion object {
        fun fromDomainModel(domain: GrantOpportunity): GrantOpportunityEntity = GrantOpportunityEntity(
            id = domain.id,
            title = domain.title,
            agency = domain.agency,
            assistanceListingNumber = domain.assistanceListingNumber,
            fundingCeilingCents = domain.fundingCeilingCents,
            fundingFloorCents = domain.fundingFloorCents,
            matchRequirementPercentage = domain.matchRequirementPercentage,
            eligibleEntityTypes = domain.eligibleEntityTypes,
            targetNaicsCodes = domain.targetNaicsCodes,
            closingDateEpochMs = domain.closingDateEpochMs,
            solicitationUrl = domain.solicitationUrl,
            summary = domain.summary
        )
    }
}
