package com.msaasd.progresspro.models.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.msaasd.progresspro.models.entities.User
import com.msaasd.progresspro.models.entities.relations.UserWithBadges
import com.msaasd.progresspro.models.entities.relations.UserWithTasks

@Dao
interface UserDao {

    // Inserta uno o varios usuarios en la base de datos. Reemplaza si ya existen.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg users: User)

    // Actualiza los datos de uno o varios usuarios.
    @Update
    suspend fun update(vararg users: User)

    // Obtiene un usuario (el primero que encuentre) de la base de datos.
    @Transaction
    @Query("SELECT * FROM ${User.TABLE_NAME} LIMIT 1")
    suspend fun getUser(): LiveData<User>

    // Obtiene un usuario específico por su ID.
    @Transaction
    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE user_id = :userId")
    suspend fun getUser(userId: Int): LiveData<User>

    // Obtiene un usuario junto con sus tareas.
    @Transaction
    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE user_id = :userId")
    suspend fun getUserWithTasks(userId: Int): LiveData<UserWithTasks>

    // Obtiene un usuario junto con las Badges que ha desbloqueado.
    @Transaction
    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE user_id = :userId")
    suspend fun getUserWithBadges(userId: Int): LiveData<UserWithBadges>
}