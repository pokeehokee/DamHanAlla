package com.damhanalla.commandcenter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.damhanalla.commandcenter.data.local.entity.GrantApplicationEntity
import com.damhanalla.commandcenter.data.local.entity.GrantOpportunityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GrantDao {
    @Query("SELECT * FROM grant_opportunities ORDER BY closingDateEpochMs ASC")
    fun getAllOpportunities(): Flow<List<GrantOpportunityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOpportunity(opportunity: GrantOpportunityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOpportunities(opportunities: List<GrantOpportunityEntity>)

    @Query("SELECT * FROM grant_applications ORDER BY finalDeadlineEpochMs ASC")
    fun getAllApplications(): Flow<List<GrantApplicationEntity>>

    @Query("SELECT * FROM grant_applications WHERE clientId = :clientId")
    fun getApplicationsForClient(clientId: String): Flow<List<GrantApplicationEntity>>

    @Query("SELECT * FROM grant_applications WHERE finalDeadlineEpochMs BETWEEN :nowEpochMs AND :horizonEpochMs ORDER BY finalDeadlineEpochMs ASC")
    fun getUpcomingDeadlines(nowEpochMs: Long, horizonEpochMs: Long): Flow<List<GrantApplicationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApplication(application: GrantApplicationEntity)

    @Update
    suspend fun updateApplication(application: GrantApplicationEntity)

    @Query("DELETE FROM grant_applications WHERE id = :id")
    suspend fun deleteApplicationById(id: String)
}
