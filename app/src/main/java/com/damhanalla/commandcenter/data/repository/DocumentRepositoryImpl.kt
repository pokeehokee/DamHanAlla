package com.damhanalla.commandcenter.data.repository

import com.damhanalla.commandcenter.data.local.dao.DocumentDao
import com.damhanalla.commandcenter.data.local.entity.DocumentRecordEntity
import com.damhanalla.commandcenter.domain.model.DocumentRecord
import com.damhanalla.commandcenter.domain.repository.DocumentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DocumentRepositoryImpl @Inject constructor(
    private val documentDao: DocumentDao
) : DocumentRepository {
    override fun getAllDocuments(): Flow<List<DocumentRecord>> =
        documentDao.getAllDocuments().map { list -> list.map { it.toDomainModel() } }

    override fun getExpiringDocuments(windowDays: Int): Flow<List<DocumentRecord>> {
        val horizon = System.currentTimeMillis() + (windowDays.toLong() * 24 * 60 * 60 * 1000)
        return documentDao.getExpiringDocuments(horizon).map { list -> list.map { it.toDomainModel() } }
    }

    override suspend fun saveDocument(document: DocumentRecord) {
        documentDao.insertDocument(DocumentRecordEntity.fromDomainModel(document))
    }

    override suspend fun deleteDocument(id: String) {
        documentDao.deleteDocumentById(id)
    }
}
