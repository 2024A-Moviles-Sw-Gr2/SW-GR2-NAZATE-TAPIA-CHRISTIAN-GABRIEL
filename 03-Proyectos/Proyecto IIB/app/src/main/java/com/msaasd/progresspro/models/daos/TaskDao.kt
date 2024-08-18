package com.msaasd.progresspro.models.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.msaasd.progresspro.models.entities.Task
import com.msaasd.progresspro.models.entities.TaskState
import com.msaasd.progresspro.models.entities.relations.TaskWithSubtasks

@Dao
interface TaskDao {

    // Inserta una o varias tareas. Reemplaza si ya existen.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg tasks: Task)

    // Actualiza una o varias tareas existentes.
    @Update
    suspend fun update(vararg tasks: Task)

    // Elimina una o varias tareas.
    @Delete
    suspend fun delete(vararg tasks: Task)

    // Obtiene una tarea específica por su ID.
    @Transaction
    @Query("SELECT * FROM tasks WHERE task_id = :taskId")
    suspend fun getTaskById(taskId: Int): LiveData<Task>

    // Obtiene todas las tareas de un usuario específico.
    @Transaction
    @Query("SELECT * FROM tasks WHERE user_id = :userId")
    suspend fun getTasksForUser(userId: Int): LiveData<List<Task>>

    // Obtiene una tarea junto con sus subtareas.
    @Transaction
    @Query("SELECT * FROM tasks WHERE task_id = :taskId")
    suspend fun getTaskWithSubtasks(taskId: Int): LiveData<List<TaskWithSubtasks>>

    // Obtiene las tareas que están en estado 'CREATED' para un usuario específico.
    @Transaction
    @Query("SELECT * FROM tasks WHERE user_id = :userId AND state = :createdState")
    suspend fun getCreatedTasks(userId: Int, createdState: TaskState = TaskState.CREATED): LiveData<List<Task>>

    // Obtiene las tareas que están en estado 'DONE' para un usuario específico.
    @Transaction
    @Query("SELECT * FROM tasks WHERE user_id = :userId AND state = :doneState")
    suspend fun getDoneTasks(userId: Int, doneState: TaskState = TaskState.DONE): LiveData<List<Task>>

    // Obtiene las tareas que están marcadas como 'pinned' para un usuario específico.
    @Transaction
    @Query("SELECT * FROM tasks WHERE user_id = :userId AND is_pinned = 1")
    suspend fun getPinnedTasks(userId: Int): LiveData<List<Task>>
}