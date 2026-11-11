package com.damhanalla.commandcenter.data.repository

import com.damhanalla.commandcenter.data.local.dao.ClientDao
import com.damhanalla.commandcenter.data.local.entity.ClientProfileEntity
import com.damhanalla.commandcenter.domain.model.ClientProfile
import com.damhanalla.commandcenter.domain.repository.ClientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientRepositoryImpl @Inject constructor(
    private val clientDao: ClientDao
) : ClientRepository {
    override fun getClients(): Flow<List<ClientProfile>> =
        clientDao.getAllClients().map { list -> list.map { it.toDomainModel() } }

    override suspend fun getClientById(id: String): ClientProfile? =
        clientDao.getClientById(id)?.toDomainModel()

    override suspend fun saveClient(client: ClientProfile) {
        clientDao.insertClient(ClientProfileEntity.fromDomainModel(client))
    }

    override suspend fun deleteClient(id: String) {
        clientDao.deleteClientById(id)
    }
}
