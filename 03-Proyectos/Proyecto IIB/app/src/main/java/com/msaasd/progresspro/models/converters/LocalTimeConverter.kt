package com.msaasd.progresspro.models.converters

import androidx.room.TypeConverter
import com.msaasd.progresspro.util.extensions.toFormattedString
import com.msaasd.progresspro.util.extensions.toLocalTime
import java.time.LocalTime

// Convertidor para manejar la conversión entre LocalTime y String
class LocalTimeConverter {

    // Convierte una cadena (timestamp) a un objeto LocalTime
    @TypeConverter
    fun fromTimestamp(value: String): LocalTime {
        return value.toLocalTime()!!  // La extensión toLocalTime convierte la cadena a LocalTime
    }

    // Convierte un objeto LocalTime a una cadena (timestamp) para ser almacenada en la base de datos
    @TypeConverter
    fun timeToTimestamp(time: LocalTime): String {
        return time.toFormattedString()  // Convierte LocalTime a cadena con formato
    }
}
