package com.example.deber03

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat

class BCrearMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //Se instancia la ventana
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bcrear_materia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Se obtiene la informacion de las materias
        val materia = intent.getParcelableExtra<Materia>("materia")
        //Se crea el botn
        val boton = findViewById<Button>(R.id.btn_crear_materia)

        if (materia != null) {
            val nombreEditText = findViewById<EditText>(R.id.input_nombreMateria)
            val fechaInicio = findViewById<EditText>(R.id.input_fechaInicioClases)
            val creditos = findViewById<EditText>(R.id.input_cantidadAlumnos)
            val iraGeneral = findViewById<EditText>(R.id.input_ira)
            val estaActiva = findViewById<Switch>(R.id.input_estaActiva)
            boton.setText("Actualizar")

            nombreEditText.setText(materia.nombre)
            fechaInicio.setText(SimpleDateFormat("dd/MM/yyyy").format(materia.fechaInicio))
            creditos.setText(materia.creditos.toString())
            iraGeneral.setText(materia.iraGeneral.toString())
            estaActiva.isChecked = materia.estaActiva
            boton.setOnClickListener {
                actualizarMateria(materia)
            }

        } else {
            boton.setOnClickListener {
                crearMateria()
            }
        }
    }
    fun actualizarMateria(materia: Materia){
        val nombre = findViewById<EditText>(R.id.input_nombreMateria).text.toString()
        val fechaInicio = SimpleDateFormat("dd/MM/yyyy").parse(findViewById<EditText>(R.id.input_fechaInicioClases).text.toString())
        val creditos = findViewById<EditText>(R.id.input_cantidadAlumnos).text.toString().toInt()
        val iraGeneral = findViewById<EditText>(R.id.input_ira).text.toString().toDouble()
        val estaActiva = findViewById<Switch>(R.id.input_estaActiva).isChecked

        val index = BBaseDatosMemoria.arregloBMaterias.indexOfFirst { it.nombre == materia.nombre }
        // Actualiza el director con los nuevos datos
        materia.nombre = nombre
        materia.fechaInicio = fechaInicio
        materia.creditos = creditos
        materia.iraGeneral = iraGeneral
        materia.estaActiva = estaActiva

        // Reemplaza el director en la base de datos en memoria

        if (index != -1) {
            BBaseDatosMemoria.arregloBMaterias[index] = materia
        }
        setResult(RESULT_OK)
        finish()

    }
    fun crearMateria(){
        // Obtén los datos de los campos
        val nombre = findViewById<EditText>(R.id.input_nombreMateria).text.toString()
        val fechaInicio = SimpleDateFormat("dd/MM/yyyy").parse(findViewById<EditText>(R.id.input_fechaInicioClases).text.toString())
        val creditos = findViewById<EditText>(R.id.input_cantidadAlumnos).text.toString().toInt()
        val iraGeneral = findViewById<EditText>(R.id.input_ira).text.toString().toDouble()
        val estaActiva = findViewById<Switch>(R.id.input_estaActiva).isChecked

        val nuevaMateria = Materia(nombre, fechaInicio, creditos, iraGeneral, estaActiva)
        BBaseDatosMemoria.arregloBMaterias.add(nuevaMateria)

        // Devuelve un resultado indicando que se creó un nuevo director
        setResult(RESULT_OK)
        finish()
    }


}