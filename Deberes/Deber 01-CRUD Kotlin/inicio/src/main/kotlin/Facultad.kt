data class Facultad(
    var id: String,
    var nombre: String,
    var ubicacion: String,
    var numeroProfesores: Int,
    var presupuesto: Double,
    var materias: MutableList<Materia> = mutableListOf()
)