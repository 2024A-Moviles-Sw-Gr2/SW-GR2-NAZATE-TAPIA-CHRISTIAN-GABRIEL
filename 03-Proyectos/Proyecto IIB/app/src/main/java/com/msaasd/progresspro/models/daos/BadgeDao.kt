package com.msaasd.progresspro.models.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.msaasd.progresspro.models.entities.Badge

@Dao
interface BadgeDao {

    // Inserta una nueva Badge en la base de datos. Si ya existe una con el mismo ID, la reemplaza.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(badge: Badge)

    // Actualiza los datos de una Badge existente.
    @Update
    suspend fun update(badge: Badge)

    // Elimina una Badge específica de la base de datos.
    @Delete
    suspend fun deleteBadge(badge: Badge)

    // Obtiene una Badge específica por su ID.
    @Transaction
    @Query("SELECT * FROM badges WHERE badge_id = :badgeId")
    suspend fun getBadgeById(badgeId: Int): LiveData<Badge>

    // Obtiene todas las Badges de la base de datos.
    @Transaction
    @Query("SELECT * FROM badges")
    suspend fun getAllBadges(): LiveData<List<Badge>>

    // Obtiene las Badges pendientes para un usuario (aquellas que aún no ha desbloqueado).
    @Transaction
    @Query(
        "SELECT * FROM badges WHERE badge_id NOT IN" +
                " (SELECT badge_id FROM user_badge_cross_ref WHERE user_id = :userId)"
    )
    suspend fun getPendingBadgesForUser(userId: Int): LiveData<List<Badge>>

    // Obtiene la próxima Badge que el usuario puede desbloquear, según sus puntos de experiencia.
    @Query(
        "SELECT * FROM badges WHERE required_points > :userExperiencePoints " +
                "ORDER BY required_points ASC LIMIT 1"
    )
    suspend fun getNextBadgeToUnlock(userExperiencePoints: Int): LiveData<Badge?>
}