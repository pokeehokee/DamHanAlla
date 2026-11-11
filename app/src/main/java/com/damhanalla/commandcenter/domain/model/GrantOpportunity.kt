package com.damhanalla.commandcenter.domain.model

data class GrantOpportunity(
    val id: String,
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
    val formattedCeiling: String
        get() = "$%,d".format(fundingCeilingCents / 100)

    val formattedMatch: String
        get() = "${(matchRequirementPercentage * 100).toInt()}% Match Required"
}
