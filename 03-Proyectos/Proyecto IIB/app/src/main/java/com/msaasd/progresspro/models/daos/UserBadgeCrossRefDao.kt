package com.msaasd.progresspro.models.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.msaasd.progresspro.models.entities.relations.UserBadgeCrossRef

@Dao
interface UserBadgeCrossRefDao {

    // Inserta una referencia cruzada entre un usuario y una Badge, indicando que el usuario ha desbloqueado esa Badge.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserBadgeCrossRef(userBadgeCrossRef: UserBadgeCrossRef)
}