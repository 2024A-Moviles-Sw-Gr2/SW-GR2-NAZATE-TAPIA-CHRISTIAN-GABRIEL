package com.msaasd.progresspro.models.converters

import androidx.room.TypeConverter
import com.msaasd.progresspro.util.extensions.toFormattedString
import com.msaasd.progresspro.util.extensions.toLocalDate
import java.time.LocalDate

// Convertidor para manejar la conversión entre LocalDate y String
class LocalDateConverter {

    // Convierte una cadena (timestamp) a un objeto LocalDate
    @TypeConverter
    fun fromTimestamp(value: String): LocalDate {
        return value.toLocalDate()!!  // La extensión toLocalDate convierte la cadena a LocalDate
    }

    // Convierte un objeto LocalDate a una cadena (timestamp) para ser almacenada en la base de datos
    @TypeConverter
    fun dateToTimestamp(date: LocalDate): String {
        return date.toFormattedString()  // Convierte LocalDate a cadena con formato
    }
}
