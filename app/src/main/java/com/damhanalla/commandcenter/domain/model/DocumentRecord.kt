package com.damhanalla.commandcenter.domain.model

data class DocumentRecord(
    val id: String,
    val fileName: String,
    val localUri: String,
    val category: DocumentCategory,
    val linkedClientId: String?,
    val linkedApplicationId: String?,
    val expirationDateEpochMs: Long?,
    val uploadedAtEpochMs: Long,
    val fileSizeBytes: Long
) {
    fun isExpiringSoon(currentEpochMs: Long, thresholdDays: Int = 30): Boolean {
        if (expirationDateEpochMs == null) return false
        val thresholdMs = thresholdDays.toLong() * 24 * 60 * 60 * 1000
        val remaining = expirationDateEpochMs - currentEpochMs
        return remaining in 1..thresholdMs
    }
}
