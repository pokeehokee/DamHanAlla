package com.damhanalla.commandcenter.data.local

import androidx.room.TypeConverter
import com.damhanalla.commandcenter.domain.model.ApplicationStage
import com.damhanalla.commandcenter.domain.model.DocumentCategory
import com.damhanalla.commandcenter.domain.model.EntityStructure
import com.damhanalla.commandcenter.domain.model.ServiceTier
import com.damhanalla.commandcenter.domain.model.TaskPriority
import com.damhanalla.commandcenter.domain.model.TaskStatus

class Converters {
    @TypeConverter
    fun fromEntityStructureList(value: List<EntityStructure>?): String =
        value?.joinToString(",") { it.name } ?: ""

    @TypeConverter
    fun toEntityStructureList(value: String): List<EntityStructure> =
        if (value.isBlank()) emptyList() else value.split(",").mapNotNull { runCatching { EntityStructure.valueOf(it) }.getOrNull() }

    @TypeConverter
    fun fromStringList(value: List<String>?): String = value?.joinToString(",") ?: ""

    @TypeConverter
    fun toStringList(value: String): List<String> = if (value.isBlank()) emptyList() else value.split(",")

    @TypeConverter
    fun fromEntityStructure(value: EntityStructure): String = value.name

    @TypeConverter
    fun toEntityStructure(value: String): EntityStructure = EntityStructure.valueOf(value)

    @TypeConverter
    fun fromServiceTier(value: ServiceTier): String = value.name

    @TypeConverter
    fun toServiceTier(value: String): ServiceTier = ServiceTier.valueOf(value)

    @TypeConverter
    fun fromApplicationStage(value: ApplicationStage): String = value.name

    @TypeConverter
    fun toApplicationStage(value: String): ApplicationStage = ApplicationStage.valueOf(value)

    @TypeConverter
    fun fromTaskPriority(value: TaskPriority): String = value.name

    @TypeConverter
    fun toTaskPriority(value: String): TaskPriority = TaskPriority.valueOf(value)

    @TypeConverter
    fun fromTaskStatus(value: TaskStatus): String = value.name

    @TypeConverter
    fun toTaskStatus(value: String): TaskStatus = TaskStatus.valueOf(value)

    @TypeConverter
    fun fromDocumentCategory(value: DocumentCategory): String = value.name

    @TypeConverter
    fun toDocumentCategory(value: String): DocumentCategory = DocumentCategory.valueOf(value)
}
