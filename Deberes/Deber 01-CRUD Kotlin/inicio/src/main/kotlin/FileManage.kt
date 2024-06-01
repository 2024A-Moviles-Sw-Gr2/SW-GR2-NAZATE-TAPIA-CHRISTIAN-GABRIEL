import java.io.File
import java.time.LocalDate

fun guardarFacultadesEnArchivo(facultades: List<Facultad>, archivo: String) {
    val file = File(archivo)
    file.bufferedWriter().use { out ->
        facultades.forEach { facultad ->
            out.write("Facultad(id=${facultad.id}, nombre=${facultad.nombre}, ubicacion=${facultad.ubicacion}, numeroProfesores=${facultad.numeroProfesores}, presupuesto=${facultad.presupuesto})\n")
            facultad.materias.forEach { materia ->
                out.write("Materia(id=${materia.id}, nombre=${materia.nombre}, codigo=${materia.codigo}, creditos=${materia.creditos}, fechaInicio=${materia.fechaInicio}, activa=${materia.activa})\n")
            }
        }
    }
}

fun leerFacultadesDesdeArchivo(archivo: String): MutableList<Facultad> {
    val facultades = mutableListOf<Facultad>()
    val file = File(archivo)
    if (!file.exists()) return facultades

    var facultadActual: Facultad? = null

    file.forEachLine { line ->
        if (line.startsWith("Facultad")) {
            val parts = line.removePrefix("Facultad(").removeSuffix(")").split(", ")
            val id = parts[0].split("=")[1]
            val nombre = parts[1].split("=")[1]
            val ubicacion = parts[2].split("=")[1]
            val numeroProfesores = parts[3].split("=")[1].toInt()
            val presupuesto = parts[4].split("=")[1].toDouble()
            facultadActual = Facultad(id, nombre, ubicacion, numeroProfesores, presupuesto)
            facultades.add(facultadActual!!)
        } else if (line.startsWith("Materia")) {
            val parts = line.removePrefix("Materia(").removeSuffix(")").split(", ")
            val id = parts[0].split("=")[1]
            val nombre = parts[1].split("=")[1]
            val codigo = parts[2].split("=")[1]
            val creditos = parts[3].split("=")[1].toDouble()
            val fechaInicio = LocalDate.parse(parts[4].split("=")[1])
            val activa = parts[5].split("=")[1].toBoolean()
            val materia = Materia(id, nombre, codigo, creditos, fechaInicio, activa)
            facultadActual?.materias?.add(materia)
        }
    }
    return facultades
}
