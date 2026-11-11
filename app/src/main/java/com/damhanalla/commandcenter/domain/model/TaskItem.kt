package com.damhanalla.commandcenter.domain.model

data class TaskItem(
    val id: String,
    val title: String,
    val detailedInstructions: String,
    val priority: TaskPriority,
    val status: TaskStatus,
    val dueDateEpochMs: Long,
    val estimatedMinutes: Int,
    val linkedClientId: String?,
    val linkedApplicationId: String?,
    val blockerExplanation: String?
)
