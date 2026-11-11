package com.damhanalla.commandcenter.domain.repository

import com.damhanalla.commandcenter.domain.model.GrantApplication
import com.damhanalla.commandcenter.domain.model.GrantOpportunity
import kotlinx.coroutines.flow.Flow

interface GrantRepository {
    fun getOpportunities(): Flow<List<GrantOpportunity>>
    suspend fun saveOpportunity(opportunity: GrantOpportunity)
    suspend fun seedInitialOpportunities(opportunities: List<GrantOpportunity>)
    fun getApplications(): Flow<List<GrantApplication>>
    fun getUpcomingDeadlines(windowDays: Int): Flow<List<GrantApplication>>
    suspend fun saveApplication(application: GrantApplication)
    suspend fun deleteApplication(id: String)
}
