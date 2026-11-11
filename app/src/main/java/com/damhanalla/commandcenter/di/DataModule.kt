package com.damhanalla.commandcenter.di

import android.content.Context
import androidx.room.Room
import com.damhanalla.commandcenter.data.local.CommandCenterDatabase
import com.damhanalla.commandcenter.data.local.dao.ClientDao
import com.damhanalla.commandcenter.data.local.dao.DocumentDao
import com.damhanalla.commandcenter.data.local.dao.GrantDao
import com.damhanalla.commandcenter.data.local.dao.TaskDao
import com.damhanalla.commandcenter.data.repository.ClientRepositoryImpl
import com.damhanalla.commandcenter.data.repository.DocumentRepositoryImpl
import com.damhanalla.commandcenter.data.repository.GrantRepositoryImpl
import com.damhanalla.commandcenter.data.repository.TaskRepositoryImpl
import com.damhanalla.commandcenter.domain.repository.ClientRepository
import com.damhanalla.commandcenter.domain.repository.DocumentRepository
import com.damhanalla.commandcenter.domain.repository.GrantRepository
import com.damhanalla.commandcenter.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindingModule {
    @Binds
    @Singleton
    abstract fun bindGrantRepository(impl: GrantRepositoryImpl): GrantRepository

    @Binds
    @Singleton
    abstract fun bindClientRepository(impl: ClientRepositoryImpl): ClientRepository

    @Binds
    @Singleton
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository

    @Binds
    @Singleton
    abstract fun bindDocumentRepository(impl: DocumentRepositoryImpl): DocumentRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CommandCenterDatabase {
        return Room.databaseBuilder(
            context,
            CommandCenterDatabase::class.java,
            "damhanalla_command_center.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideGrantDao(database: CommandCenterDatabase): GrantDao = database.grantDao()

    @Provides
    fun provideClientDao(database: CommandCenterDatabase): ClientDao = database.clientDao()

    @Provides
    fun provideTaskDao(database: CommandCenterDatabase): TaskDao = database.taskDao()

    @Provides
    fun provideDocumentDao(database: CommandCenterDatabase): DocumentDao = database.documentDao()
}
