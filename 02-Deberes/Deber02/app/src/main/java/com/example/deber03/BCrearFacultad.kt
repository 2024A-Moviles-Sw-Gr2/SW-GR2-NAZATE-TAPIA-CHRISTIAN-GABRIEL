package com.example.deber03

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat

class BCrearFacultad : AppCompatActivity() {


    //Pantalla para crear Facultad
    override fun onCreate(savedInstanceState: Bundle?) {
        //Se instancia la pantalla
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bcrear_facultad)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_crear_facultad)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Informacion extra
        AlertDialog.Builder(this).setMessage("entrando a crear Facultad")

        //Se obtiene la facultad enviada desde la actividad pricipal
        val facultad = intent.getParcelableExtra<Facultad>("facultad")
        //Se establece el boton
        val boton = findViewById<Button>(R.id.btn_crear)

        //En el caso de que exista una facultad al instanciar la pantalla
        //Es decir cuando se quiere editar la info
        //Se carga los datos en la pantalla para quie los vea el usuario
        if (facultad != null) {
            val nombreEditText = findViewById<EditText>(R.id.input_nombre)
            val fechaMatriculasEditText=findViewById<EditText>(R.id.input_fechaMatriculas)
            val numeroProfesoresEditText = findViewById<EditText>(R.id.input_numeroProfesores)
            val presupuestoEditText = findViewById<EditText>(R.id.input_presupuesto)
            val enClasesCheckBox = findViewById<Switch>(R.id.input_estaEnClases)
            boton.setText("Actualizar")

            nombreEditText.setText(facultad.nombre)
            fechaMatriculasEditText.setText(SimpleDateFormat("dd/MM/yyyy").format(facultad.fechaMatriculas))
            numeroProfesoresEditText.setText(facultad.numeroProfesores.toString())
            presupuestoEditText.setText(facultad.presupuesto.toString())
            enClasesCheckBox.isChecked = facultad.enClases
            //Alñ pulsar el boton se ejecuta la funcion de actualizar... solo si es una facultad
            //para actualizar
            boton.setOnClickListener {
                actualizarFacultad(facultad)
            }

        } else {
            //En caso de que no exista una facultad como mensaje simplemente se crea una
            boton.setOnClickListener {
                crearFacultad()
            }
        }
    }



    private fun actualizarFacultad(facultad: Facultad) {

        val nombre = findViewById<EditText>(R.id.input_nombre).text.toString()
        val fechaMatriculas = SimpleDateFormat("dd/MM/yyyy").parse(findViewById<EditText>(R.id.input_fechaMatriculas).text.toString())
        val numeroProfesores = findViewById<EditText>(R.id.input_numeroProfesores).text.toString().toInt()
        val presupuesto = findViewById<EditText>(R.id.input_presupuesto).text.toString().toDouble()
        val enClases = findViewById<Switch>(R.id.input_estaEnClases).isChecked

        val index = BBaseDatosMemoria.arregloBFacultad.indexOfFirst { it.nombre == facultad.nombre }
        // Actualiza el facultad con los nuevos datos
        facultad.nombre = nombre
        facultad.fechaMatriculas = fechaMatriculas
        facultad.numeroProfesores = numeroProfesores
        facultad.presupuesto = presupuesto
        facultad.enClases = enClases

        // Reemplaza el facultad en la base de datos en memoria

        if (index != -1) {
            BBaseDatosMemoria.arregloBFacultad[index] = facultad
        }
        setResult(RESULT_OK)
        finish()
    }


    private fun crearFacultad() {
        // Obtén los datos de los campos
        val nombre = findViewById<EditText>(R.id.input_nombre).text.toString()
        val fechaMatriculas = SimpleDateFormat("dd/MM/yyyy").parse(findViewById<EditText>(R.id.input_fechaMatriculas).text.toString())
        val numeroProfesores = findViewById<EditText>(R.id.input_numeroProfesores).text.toString().toInt()
        val presupuesto = findViewById<EditText>(R.id.input_presupuesto).text.toString().toDouble()
        val enClases = findViewById<Switch>(R.id.input_estaEnClases).isChecked

        val nuevoFacultad = Facultad(nombre, fechaMatriculas, numeroProfesores, presupuesto, enClases)
        BBaseDatosMemoria.arregloBFacultad.add(nuevoFacultad)

        // Devuelve un resultado indicando que se creó un nuevo director
        setResult(RESULT_OK)
        finish()
    }

}
