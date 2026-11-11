package com.damhanalla.commandcenter.domain.repository

import com.damhanalla.commandcenter.domain.model.TaskItem
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getActiveTasks(): Flow<List<TaskItem>>
    suspend fun saveTask(task: TaskItem)
    suspend fun completeTask(id: String)
    suspend fun deleteTask(id: String)
}
