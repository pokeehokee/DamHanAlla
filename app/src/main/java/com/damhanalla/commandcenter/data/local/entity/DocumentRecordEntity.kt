package com.damhanalla.commandcenter.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.damhanalla.commandcenter.domain.model.DocumentCategory
import com.damhanalla.commandcenter.domain.model.DocumentRecord

@Entity(
    tableName = "documents",
    indices = [Index("linkedClientId"), Index("linkedApplicationId"), Index("category")]
)
data class DocumentRecordEntity(
    @PrimaryKey val id: String,
    val fileName: String,
    val localUri: String,
    val category: DocumentCategory,
    val linkedClientId: String?,
    val linkedApplicationId: String?,
    val expirationDateEpochMs: Long?,
    val uploadedAtEpochMs: Long,
    val fileSizeBytes: Long
) {
    fun toDomainModel(): DocumentRecord = DocumentRecord(
        id = id,
        fileName = fileName,
        localUri = localUri,
        category = category,
        linkedClientId = linkedClientId,
        linkedApplicationId = linkedApplicationId,
        expirationDateEpochMs = expirationDateEpochMs,
        uploadedAtEpochMs = uploadedAtEpochMs,
        fileSizeBytes = fileSizeBytes
    )

    companion object {
        fun fromDomainModel(domain: DocumentRecord): DocumentRecordEntity = DocumentRecordEntity(
            id = domain.id,
            fileName = domain.fileName,
            localUri = domain.localUri,
            category = domain.category,
            linkedClientId = domain.linkedClientId,
            linkedApplicationId = domain.linkedApplicationId,
            expirationDateEpochMs = domain.expirationDateEpochMs,
            uploadedAtEpochMs = domain.uploadedAtEpochMs,
            fileSizeBytes = domain.fileSizeBytes
        )
    }
}
