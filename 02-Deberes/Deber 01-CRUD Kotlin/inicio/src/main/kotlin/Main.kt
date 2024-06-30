import java.time.LocalDate

fun main() {
    val facultades = mutableListOf<Facultad>()
    while (true) {
        println("Seleccione una opción:")
        println("1. Crear nueva facultad")
        println("2. Leer todas las facultades")
        println("3. Actualizar una facultad")
        println("4. Eliminar una facultad")
        println("5. Crear nueva materia")
        println("6. Leer todas las materias de una facultad")
        println("7. Actualizar una materia")
        println("8. Eliminar una materia")
        println("9. Guardar facultades en archivo")
        println("10. Leer facultades desde archivo")
        println("0. Salir")

        when (readLine()!!.toInt()) {
            1 -> {
                println("Ingrese el ID de la facultad:")
                val id = readln()
                println("Ingrese el nombre de la facultad:")
                val nombre = readLine()!!
                println("Ingrese la ubicación de la facultad:")
                val ubicacion = readLine()!!
                println("Ingrese el número de profesores:")
                val numeroProfesores = readLine()!!.toInt()
                println("Ingrese el presupuesto:")
                val presupuesto = readLine()!!.toDouble()

                val nuevaFacultad = Facultad(id, nombre, ubicacion, numeroProfesores, presupuesto)
                crearFacultad(facultades, nuevaFacultad)
            }
            2 -> leerFacultades(facultades)
            3 -> {
                println("Ingrese el ID de la facultad a actualizar:")
                val id = readLine()!!
                println("Ingrese el nuevo nombre de la facultad:")
                val nombre = readLine()!!
                println("Ingrese la nueva ubicación de la facultad:")
                val ubicacion = readLine()!!
                println("Ingrese el nuevo número de profesores:")
                val numeroProfesores = readLine()!!.toInt()
                println("Ingrese el nuevo presupuesto:")
                val presupuesto = readLine()!!.toDouble()

                val facultadActualizada = Facultad(id, nombre, ubicacion, numeroProfesores, presupuesto)
                actualizarFacultad(facultades, id, facultadActualizada)
            }
            4 -> {
                println("Ingrese el ID de la facultad a eliminar:")
                val id = readLine()!!
                eliminarFacultad(facultades, id)
            }
            5 -> {
                println("Ingrese el ID de la facultad a la que desea agregar la materia:")
                val facultadId = readLine()!!
                val facultad = facultades.find { it.id == facultadId }

                if (facultad != null) {
                    println("Ingrese el ID de la materia:")
                    val id = readLine()!!
                    println("Ingrese el nombre de la materia:")
                    val nombre = readLine()!!
                    println("Ingrese el código de la materia:")
                    val codigo = readLine()!!
                    println("Ingrese los créditos de la materia:")
                    val creditos = readLine()!!.toDouble()
                    println("Ingrese la fecha de inicio (YYYY-MM-DD):")
                    val fechaInicio = LocalDate.parse(readLine()!!)
                    println("¿La materia está activa? (true/false):")
                    val activa = readLine()!!.toBoolean()

                    val nuevaMateria = Materia(id, nombre, codigo, creditos, fechaInicio, activa)
                    crearMateria(facultad, nuevaMateria)
                } else {
                    println("Facultad con ID $facultadId no encontrada.")
                }
            }
            6 -> {
                println("Ingrese el ID de la facultad cuyas materias desea leer:")
                val facultadId = readLine()!!
                val facultad = facultades.find { it.id == facultadId }

                if (facultad != null) {
                    leerMaterias(facultad)
                } else {
                    println("Facultad con ID $facultadId no encontrada.")
                }
            }
            7 -> {
                println("Ingrese el ID de la facultad a la que pertenece la materia:")
                val facultadId = readLine()!!
                val facultad = facultades.find { it.id == facultadId }

                if (facultad != null) {
                    println("Ingrese el ID de la materia a actualizar:")
                    val materiaId = readLine()!!
                    println("Ingrese el nuevo nombre de la materia:")
                    val nombre = readLine()!!
                    println("Ingrese el nuevo código de la materia:")
                    val codigo = readLine()!!
                    println("Ingrese los nuevos créditos de la materia:")
                    val creditos = readLine()!!.toDouble()
                    println("Ingrese la nueva fecha de inicio (YYYY-MM-DD):")
                    val fechaInicio = LocalDate.parse(readLine()!!)
                    println("¿La materia está activa? (true/false):")
                    val activa = readLine()!!.toBoolean()

                    val materiaActualizada = Materia(materiaId, nombre, codigo, creditos, fechaInicio, activa)
                    actualizarMateria(facultad, materiaId, materiaActualizada)
                } else {
                    println("Facultad con ID $facultadId no encontrada.")
                }
            }
            8 -> {
                println("Ingrese el ID de la facultad a la que pertenece la materia:")
                val facultadId = readLine()!!
                val facultad = facultades.find { it.id == facultadId }

                if (facultad != null) {
                    println("Ingrese el ID de la materia a eliminar:")
                    val materiaId = readLine()!!
                    eliminarMateria(facultad, materiaId)
                } else {
                    println("Facultad con ID $facultadId no encontrada.")
                }
            }
            9 -> {
                val archivo = "data.txt"
                guardarFacultadesEnArchivo(facultades, archivo)
                println("Facultades guardadas en $archivo")
            }
            10 -> {
                val archivo = "data.txt"
                val facultadesDesdeArchivo = leerFacultadesDesdeArchivo(archivo)
                facultades.clear()
                facultades.addAll(facultadesDesdeArchivo)
                println("Facultades cargadas desde $archivo")
            }
            0 -> return
            else -> println("Opción no válida. Intente de nuevo.")
        }
    }
}
