package com.msaasd.progresspro.models.converters

import androidx.room.TypeConverter
import com.msaasd.progresspro.models.entities.TaskState

// Convertidor para manejar la conversión entre el enum TaskState y String
class TaskStateConverter {

    // Convierte un objeto TaskState a su representación en cadena
    @TypeConverter
    fun fromTaskState(taskState: TaskState): String {
        return taskState.name  // Retorna el nombre del enum TaskState como cadena
    }

    // Convierte una cadena a un objeto TaskState
    @TypeConverter
    fun toTaskState(taskState: String): TaskState {
        return enumValueOf<TaskState>(taskState)  // Convierte la cadena a su correspondiente valor del enum TaskState
    }
}
