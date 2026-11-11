package com.damhanalla.commandcenter.domain.model

data class ClientProfile(
    val id: String,
    val legalBusinessName: String,
    val dbaName: String?,
    val structure: EntityStructure,
    val primaryNaics: String,
    val federalEin: String,
    val samGovUei: String?,
    val cageCode: String?,
    val physicalCity: String,
    val physicalState: String,
    val primaryContactName: String,
    val primaryContactEmail: String,
    val primaryContactPhone: String,
    val annualGrossRevenueCents: Long,
    val employeeCount: Int,
    val activeServiceTier: ServiceTier,
    val agreedSuccessBonusPercentage: Double,
    val initialAuditFeePaid: Boolean,
    val isNdSigned: Boolean,
    val onboardingDateEpochMs: Long
)
