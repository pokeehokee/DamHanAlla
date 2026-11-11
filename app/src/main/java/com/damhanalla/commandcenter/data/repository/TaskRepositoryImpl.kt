package com.damhanalla.commandcenter.data.repository

import com.damhanalla.commandcenter.data.local.dao.TaskDao
import com.damhanalla.commandcenter.data.local.entity.TaskItemEntity
import com.damhanalla.commandcenter.domain.model.TaskItem
import com.damhanalla.commandcenter.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun getActiveTasks(): Flow<List<TaskItem>> =
        taskDao.getActiveTasks().map { list -> list.map { it.toDomainModel() } }

    override suspend fun saveTask(task: TaskItem) {
        taskDao.insertTask(TaskItemEntity.fromDomainModel(task))
    }

    override suspend fun completeTask(id: String) {
        taskDao.markTaskCompleted(id)
    }

    override suspend fun deleteTask(id: String) {
        taskDao.deleteTaskById(id)
    }
}
