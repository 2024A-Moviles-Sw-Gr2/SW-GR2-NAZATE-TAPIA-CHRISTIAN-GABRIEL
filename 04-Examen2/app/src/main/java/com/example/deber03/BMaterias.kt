package com.example.deber03


import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BMaterias : AppCompatActivity() {
    //Se carga el arreglo de materias
    val arreglo = BBaseDatosMemoria.arregloBMaterias
    private lateinit var adaptador: ArrayAdapter<Materia>
    //identifica que elemento en el List de materias se escoge
    private var posicionItemSeleccionado = -1 // Declara la variable aquí

    override fun onCreate(savedInstanceState: Bundle?) {
        //Se instancia la ventana
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmaterias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_materias)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Se setea la lista de elementos con el contenido del adaptador, en este caso las materias

        val listView = findViewById<ListView>(R.id.lv_list_view_materias)
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, // layout xml a usar
            arreglo
        )
        listView.adapter = adaptador
//Funcionamiento del boton para Crear una MAteria
        val botonAnadirListView = findViewById<Button>(R.id.btn_crearMateria)
        botonAnadirListView.setOnClickListener {
            //Nos redirije a la ventana de creacion de materias
            irActividad(BCrearMateria::class.java, MainActivity.REQUEST_CODE_ADD_OR_EDIT)
        }
        registerForContextMenu(listView) // NUEVA LINEA

        val facultad = intent.getParcelableExtra<Facultad>("facultad")
        //se coloca en pantalla el nombre de la cfacultad si es que esta lo tiene,
        //En el caso de no tenerla se coloca un nombre alternativo.. no disponible
        val textoFacultad = findViewById<TextView>(R.id.txt_facultad)
        textoFacultad.text = facultad?.nombre ?: "Nombre no disponible"



    }


    // Maneja la informacion de las actifividades, cuando se añade una nueva Materia
    // se actualiza la lista de Materias de la actifidad pricipal
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MainActivity.REQUEST_CODE_ADD_OR_EDIT && resultCode == RESULT_OK) {
            adaptador.notifyDataSetChanged() // Actualizar la informacion
        }
    }


    //Creacion del menu contextual.
    //El menu simplemente se instancia al seleccionar un elemento de la lista
    //Las opciones estan predefinidas en Elminar, Editar

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu,v,menuInfo)
        // llenamos opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu2, menu)
        // Obtener id
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                // Obtener La materia seleccionado
                val materiaSeleccionada = arreglo[posicionItemSeleccionado]
                // Crear el Intent y pasar La materia Al menu de creacion como informacion
                val intent = Intent(this, BCrearMateria::class.java)
                intent.putExtra("materia", materiaSeleccionada)
                startActivityForResult(intent, MainActivity.REQUEST_CODE_ADD_OR_EDIT)
                adaptador.notifyDataSetChanged()
                true
            }
            //en caso de eliminar directamente se lo elimina del arreglo
            R.id.mi_eliminar -> {
                val materiaSeleccionada = arreglo[posicionItemSeleccionado]
                BBaseDatosMemoria.arregloBMaterias.remove(materiaSeleccionada)
                adaptador.notifyDataSetChanged()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }
    //Funcion para moverse entre actividades, en este caso solo a Crear Nueva materia
    private fun irActividad(clase: Class<*>, requestCode: Int) {
        val intent = Intent(this, clase)
        startActivityForResult(intent, requestCode)
    }
}