package com.damhanalla.commandcenter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.damhanalla.commandcenter.data.local.entity.ClientProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Query("SELECT * FROM clients ORDER BY legalBusinessName ASC")
    fun getAllClients(): Flow<List<ClientProfileEntity>>

    @Query("SELECT * FROM clients WHERE id = :id LIMIT 1")
    suspend fun getClientById(id: String): ClientProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: ClientProfileEntity)

    @Update
    suspend fun updateClient(client: ClientProfileEntity)

    @Query("DELETE FROM clients WHERE id = :id")
    suspend fun deleteClientById(id: String)
}
