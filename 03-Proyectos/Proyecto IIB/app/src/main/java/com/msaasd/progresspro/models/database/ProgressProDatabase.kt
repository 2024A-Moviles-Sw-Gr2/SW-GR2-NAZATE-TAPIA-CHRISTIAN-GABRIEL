package com.msaasd.progresspro.models.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.msaasd.progresspro.models.converters.LocalDateConverter
import com.msaasd.progresspro.models.converters.LocalDateTimeConverter
import com.msaasd.progresspro.models.converters.LocalTimeConverter
import com.msaasd.progresspro.models.converters.TaskStateConverter
import com.msaasd.progresspro.models.daos.BadgeDao
import com.msaasd.progresspro.models.daos.SubtaskDao
import com.msaasd.progresspro.models.daos.TaskDao
import com.msaasd.progresspro.models.daos.UserBadgeCrossRefDao
import com.msaasd.progresspro.models.daos.UserDao
import com.msaasd.progresspro.models.database.Badges.BADGES
import com.msaasd.progresspro.models.entities.Badge
import com.msaasd.progresspro.models.entities.Subtask
import com.msaasd.progresspro.models.entities.Task
import com.msaasd.progresspro.models.entities.User
import com.msaasd.progresspro.models.entities.relations.UserBadgeCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    version = 1,
    entities = [
        Badge::class,
        Subtask::class,
        Task::class,
        User::class
    ]
)
@TypeConverters(
    LocalDateConverter::class,
    LocalDateTimeConverter::class,
    LocalTimeConverter::class,
    TaskStateConverter::class,
    UserBadgeCrossRef::class
)
abstract class ProgressProDatabase : RoomDatabase() {

    companion object {
        // Mantiene una referencia única de la base de datos
        @Volatile
        private var INSTANCE: ProgressProDatabase? = null

        // Obtiene una instancia de la base de datos, creando una nueva si no existe
        fun getDatabase(context: Context, scope: CoroutineScope): ProgressProDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProgressProDatabase::class.java,
                    "progresspro_db"  // Nombre del archivo de la base de datos
                ).addCallback(
                    ProgressProDatabaseCallback(scope)  // Callback para inicialización
                ).build().also {
                    INSTANCE = it  // Asigna la instancia creada a INSTANCE
                }
            }
        }
    }

    // DAOs abstractos para acceso a la base de datos
    abstract fun badgeDao(): BadgeDao
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
    abstract fun subtaskDao(): SubtaskDao
    abstract fun userBadgeCrossRefDao(): UserBadgeCrossRefDao

    // Callback para realizar acciones al crear la base de datos por primera vez
    private class ProgressProDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        // Método llamado cuando la base de datos se crea por primera vez
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                // Inicia la base de datos con datos iniciales en un hilo separado
                scope.launch {
                    populateBadges(database.badgeDao())  // Población inicial de badges
                    database.userDao().insert(
                        User(
                            firstName = "Adrián",  // Inserta un usuario de ejemplo
                            lastName = "Egüez"
                        )
                    )
                }
            }
        }

        // Método para poblar la base de datos con Badges predefinidos
        private suspend fun populateBadges(badgeDao: BadgeDao) {
            BADGES.forEach {
                badgeDao.insert(it)  // Inserta cada Badge de la lista BADGES
            }
        }
    }
}