package com.damhanalla.commandcenter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.damhanalla.commandcenter.domain.model.ClientProfile
import com.damhanalla.commandcenter.domain.model.EntityStructure
import com.damhanalla.commandcenter.domain.model.ServiceTier

@Entity(tableName = "clients")
data class ClientProfileEntity(
    @PrimaryKey val id: String,
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
) {
    fun toDomainModel(): ClientProfile = ClientProfile(
        id = id,
        legalBusinessName = legalBusinessName,
        dbaName = dbaName,
        structure = structure,
        primaryNaics = primaryNaics,
        federalEin = federalEin,
        samGovUei = samGovUei,
        cageCode = cageCode,
        physicalCity = physicalCity,
        physicalState = physicalState,
        primaryContactName = primaryContactName,
        primaryContactEmail = primaryContactEmail,
        primaryContactPhone = primaryContactPhone,
        annualGrossRevenueCents = annualGrossRevenueCents,
        employeeCount = employeeCount,
        activeServiceTier = activeServiceTier,
        agreedSuccessBonusPercentage = agreedSuccessBonusPercentage,
        initialAuditFeePaid = initialAuditFeePaid,
        isNdSigned = isNdSigned,
        onboardingDateEpochMs = onboardingDateEpochMs
    )

    companion object {
        fun fromDomainModel(domain: ClientProfile): ClientProfileEntity = ClientProfileEntity(
            id = domain.id,
            legalBusinessName = domain.legalBusinessName,
            dbaName = domain.dbaName,
            structure = domain.structure,
            primaryNaics = domain.primaryNaics,
            federalEin = domain.federalEin,
            samGovUei = domain.samGovUei,
            cageCode = domain.cageCode,
            physicalCity = domain.physicalCity,
            physicalState = domain.physicalState,
            primaryContactName = domain.primaryContactName,
            primaryContactEmail = domain.primaryContactEmail,
            primaryContactPhone = domain.primaryContactPhone,
            annualGrossRevenueCents = domain.annualGrossRevenueCents,
            employeeCount = domain.employeeCount,
            activeServiceTier = domain.activeServiceTier,
            agreedSuccessBonusPercentage = domain.agreedSuccessBonusPercentage,
            initialAuditFeePaid = domain.initialAuditFeePaid,
            isNdSigned = domain.isNdSigned,
            onboardingDateEpochMs = domain.onboardingDateEpochMs
        )
    }
}
