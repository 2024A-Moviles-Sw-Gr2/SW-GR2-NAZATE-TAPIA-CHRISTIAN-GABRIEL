import java.time.LocalDate

data class Materia(
    var id: String,
    var nombre: String,
    var codigo: String,
    var creditos: Double,
    var fechaInicio: LocalDate,
    var activa: Boolean
)
