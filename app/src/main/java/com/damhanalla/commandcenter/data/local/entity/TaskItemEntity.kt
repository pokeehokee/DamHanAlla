package com.damhanalla.commandcenter.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.damhanalla.commandcenter.domain.model.TaskItem
import com.damhanalla.commandcenter.domain.model.TaskPriority
import com.damhanalla.commandcenter.domain.model.TaskStatus

@Entity(
    tableName = "tasks",
    indices = [Index("linkedClientId"), Index("linkedApplicationId"), Index("status")]
)
data class TaskItemEntity(
    @PrimaryKey val id: String,
    val title: String,
    val detailedInstructions: String,
    val priority: TaskPriority,
    val status: TaskStatus,
    val dueDateEpochMs: Long,
    val estimatedMinutes: Int,
    val linkedClientId: String?,
    val linkedApplicationId: String?,
    val blockerExplanation: String?
) {
    fun toDomainModel(): TaskItem = TaskItem(
        id = id,
        title = title,
        detailedInstructions = detailedInstructions,
        priority = priority,
        status = status,
        dueDateEpochMs = dueDateEpochMs,
        estimatedMinutes = estimatedMinutes,
        linkedClientId = linkedClientId,
        linkedApplicationId = linkedApplicationId,
        blockerExplanation = blockerExplanation
    )

    companion object {
        fun fromDomainModel(domain: TaskItem): TaskItemEntity = TaskItemEntity(
            id = domain.id,
            title = domain.title,
            detailedInstructions = domain.detailedInstructions,
            priority = domain.priority,
            status = domain.status,
            dueDateEpochMs = domain.dueDateEpochMs,
            estimatedMinutes = domain.estimatedMinutes,
            linkedClientId = domain.linkedClientId,
            linkedApplicationId = domain.linkedApplicationId,
            blockerExplanation = domain.blockerExplanation
        )
    }
}
