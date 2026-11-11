package com.damhanalla.commandcenter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.damhanalla.commandcenter.data.local.entity.DocumentRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao {
    @Query("SELECT * FROM documents ORDER BY uploadedAtEpochMs DESC")
    fun getAllDocuments(): Flow<List<DocumentRecordEntity>>

    @Query("SELECT * FROM documents WHERE linkedClientId = :clientId")
    fun getDocumentsForClient(clientId: String): Flow<List<DocumentRecordEntity>>

    @Query("SELECT * FROM documents WHERE expirationDateEpochMs IS NOT NULL AND expirationDateEpochMs <= :expirationThresholdEpochMs ORDER BY expirationDateEpochMs ASC")
    fun getExpiringDocuments(expirationThresholdEpochMs: Long): Flow<List<DocumentRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocument(document: DocumentRecordEntity)

    @Query("DELETE FROM documents WHERE id = :id")
    suspend fun deleteDocumentById(id: String)
}
