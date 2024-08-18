package com.msaasd.progresspro.models.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.msaasd.progresspro.models.entities.Subtask

@Dao
interface SubtaskDao {

    // Inserta una nueva Subtask. Si ya existe, la reemplaza.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subtask: Subtask)

    // Actualiza los datos de una Subtask existente.
    @Update
    suspend fun update(subtask: Subtask)

    // Elimina una Subtask específica.
    @Delete
    suspend fun delete(subtask: Subtask)

    // Obtiene una Subtask por su ID.
    @Transaction
    @Query("SELECT * FROM subtasks WHERE subtask_id = :subtaskId")
    suspend fun getSubtaskById(subtaskId: Int): LiveData<Subtask>

    // Obtiene todas las Subtasks de una Task específica.
    @Transaction
    @Query("SELECT * FROM subtasks WHERE task_id = :taskId")
    suspend fun getSubtasksForTask(taskId: Int): LiveData<List<Subtask>>

    // Obtiene las Subtasks pendientes para una Task (aquellas que aún no están completadas).
    @Transaction
    @Query("SELECT * FROM subtasks WHERE task_id = :taskId AND is_complete = 0")
    suspend fun getPendingSubtasksForTask(taskId: Int): LiveData<List<Subtask>>
}