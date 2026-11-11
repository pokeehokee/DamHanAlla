package com.damhanalla.commandcenter.domain.repository

import com.damhanalla.commandcenter.domain.model.DocumentRecord
import kotlinx.coroutines.flow.Flow

interface DocumentRepository {
    fun getAllDocuments(): Flow<List<DocumentRecord>>
    fun getExpiringDocuments(windowDays: Int): Flow<List<DocumentRecord>>
    suspend fun saveDocument(document: DocumentRecord)
    suspend fun deleteDocument(id: String)
}
