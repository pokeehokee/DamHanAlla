package com.damhanalla.commandcenter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.damhanalla.commandcenter.data.local.dao.ClientDao
import com.damhanalla.commandcenter.data.local.dao.DocumentDao
import com.damhanalla.commandcenter.data.local.dao.GrantDao
import com.damhanalla.commandcenter.data.local.dao.TaskDao
import com.damhanalla.commandcenter.data.local.entity.ClientProfileEntity
import com.damhanalla.commandcenter.data.local.entity.DocumentRecordEntity
import com.damhanalla.commandcenter.data.local.entity.GrantApplicationEntity
import com.damhanalla.commandcenter.data.local.entity.GrantOpportunityEntity
import com.damhanalla.commandcenter.data.local.entity.TaskItemEntity

@Database(
    entities = [
        GrantOpportunityEntity::class,
        GrantApplicationEntity::class,
        ClientProfileEntity::class,
        TaskItemEntity::class,
        DocumentRecordEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CommandCenterDatabase : RoomDatabase() {
    abstract fun grantDao(): GrantDao
    abstract fun clientDao(): ClientDao
    abstract fun taskDao(): TaskDao
    abstract fun documentDao(): DocumentDao
}
