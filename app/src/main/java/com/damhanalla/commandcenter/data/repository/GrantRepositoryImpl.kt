package com.damhanalla.commandcenter.data.repository

import com.damhanalla.commandcenter.data.local.dao.GrantDao
import com.damhanalla.commandcenter.data.local.entity.GrantApplicationEntity
import com.damhanalla.commandcenter.data.local.entity.GrantOpportunityEntity
import com.damhanalla.commandcenter.domain.model.GrantApplication
import com.damhanalla.commandcenter.domain.model.GrantOpportunity
import com.damhanalla.commandcenter.domain.repository.GrantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GrantRepositoryImpl @Inject constructor(
    private val grantDao: GrantDao
) : GrantRepository {
    override fun getOpportunities(): Flow<List<GrantOpportunity>> =
        grantDao.getAllOpportunities().map { list -> list.map { it.toDomainModel() } }

    override suspend fun saveOpportunity(opportunity: GrantOpportunity) {
        grantDao.insertOpportunity(GrantOpportunityEntity.fromDomainModel(opportunity))
    }

    override suspend fun seedInitialOpportunities(opportunities: List<GrantOpportunity>) {
        grantDao.insertOpportunities(opportunities.map { GrantOpportunityEntity.fromDomainModel(it) })
    }

    override fun getApplications(): Flow<List<GrantApplication>> =
        grantDao.getAllApplications().map { list -> list.map { it.toDomainModel() } }

    override fun getUpcomingDeadlines(windowDays: Int): Flow<List<GrantApplication>> {
        val now = System.currentTimeMillis()
        val horizon = now + (windowDays.toLong() * 24 * 60 * 60 * 1000)
        return grantDao.getUpcomingDeadlines(now, horizon).map { list -> list.map { it.toDomainModel() } }
    }

    override suspend fun saveApplication(application: GrantApplication) {
        grantDao.insertApplication(GrantApplicationEntity.fromDomainModel(application))
    }

    override suspend fun deleteApplication(id: String) {
        grantDao.deleteApplicationById(id)
    }
}
