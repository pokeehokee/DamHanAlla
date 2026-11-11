package com.damhanalla.commandcenter.domain.repository

import com.damhanalla.commandcenter.domain.model.ClientProfile
import kotlinx.coroutines.flow.Flow

interface ClientRepository {
    fun getClients(): Flow<List<ClientProfile>>
    suspend fun getClientById(id: String): ClientProfile?
    suspend fun saveClient(client: ClientProfile)
    suspend fun deleteClient(id: String)
}
