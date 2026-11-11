package com.damhanalla.commandcenter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.damhanalla.commandcenter.data.local.entity.TaskItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE status != 'COMPLETED' ORDER BY dueDateEpochMs ASC")
    fun getActiveTasks(): Flow<List<TaskItemEntity>>

    @Query("SELECT * FROM tasks WHERE linkedClientId = :clientId")
    fun getTasksForClient(clientId: String): Flow<List<TaskItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskItemEntity)

    @Update
    suspend fun updateTask(task: TaskItemEntity)

    @Query("UPDATE tasks SET status = 'COMPLETED' WHERE id = :id")
    suspend fun markTaskCompleted(id: String)

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTaskById(id: String)
}
