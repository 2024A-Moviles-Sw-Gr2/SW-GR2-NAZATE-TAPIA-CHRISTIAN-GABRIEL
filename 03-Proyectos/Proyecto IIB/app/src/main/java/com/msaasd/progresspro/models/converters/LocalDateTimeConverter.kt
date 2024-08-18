package com.msaasd.progresspro.models.converters

import androidx.room.TypeConverter
import com.msaasd.progresspro.util.extensions.toFormattedString
import com.msaasd.progresspro.util.extensions.toLocalDateTime
import java.time.LocalDateTime

// Convertidor para manejar la conversión entre LocalDateTime y String
class LocalDateTimeConverter {

    // Convierte una cadena (timestamp) a un objeto LocalDateTime
    @TypeConverter
    fun fromTimestamp(value: String): LocalDateTime {
        return value.toLocalDateTime()!!  // La extensión toLocalDateTime convierte la cadena a LocalDateTime
    }

    // Convierte un objeto LocalDateTime a una cadena (timestamp) para ser almacenada en la base de datos
    @TypeConverter
    fun dateTimeToTimestamp(dateTime: LocalDateTime): String {
        return dateTime.toFormattedString()  // Convierte LocalDateTime a cadena con formato
    }
}
