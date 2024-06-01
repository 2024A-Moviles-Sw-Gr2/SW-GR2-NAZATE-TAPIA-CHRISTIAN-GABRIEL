fun crearFacultad(facultades: MutableList<Facultad>, facultad: Facultad) {
    facultades.add(facultad)
}

fun leerFacultades(facultades: List<Facultad>) {
    facultades.forEach { println(it) }
}

fun actualizarFacultad(facultades: MutableList<Facultad>, id: String, nuevaFacultad: Facultad) {
    val index = facultades.indexOfFirst { it.id == id }
    if (index != -1) {
        facultades[index] = nuevaFacultad
    } else {
        println("Facultad con ID $id no encontrada.")
    }
}

fun eliminarFacultad(facultades: MutableList<Facultad>, id: String) {
    facultades.removeIf { it.id == id }
}

fun crearMateria(facultad: Facultad, materia: Materia) {
    facultad.materias.add(materia)
}

fun leerMaterias(facultad: Facultad) {
    facultad.materias.forEach { println(it) }
}

fun actualizarMateria(facultad: Facultad, id: String, nuevaMateria: Materia) {
    val index = facultad.materias.indexOfFirst { it.id == id }
    if (index != -1) {
        facultad.materias[index] = nuevaMateria
    } else {
        println("Materia con ID $id no encontrada.")
    }
}

fun eliminarMateria(facultad: Facultad, id: String) {
    facultad.materias.removeIf { it.id == id }
}
