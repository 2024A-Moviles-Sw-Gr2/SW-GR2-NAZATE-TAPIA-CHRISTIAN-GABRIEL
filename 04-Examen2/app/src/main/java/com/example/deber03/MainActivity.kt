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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //Se crear una base de datos simple... con arreglitos
    val arreglo = BBaseDatosMemoria.arregloBFacultad
    private lateinit var adaptador: ArrayAdapter<Facultad>
    //identifica que elemento en el List se escoge
    private var posicionItemSeleccionado = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        //Instanciar parametros vista principal
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Se setea la lista de elementos con el contenido del adaptador
        val listView = findViewById<ListView>(R.id.lv_list_view)
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, // layout xml a usar
            arreglo
        )

        listView.adapter = adaptador
        //Funcionamiento del boton para Crear una facultad
        val botonAnadirListView = findViewById<Button>(R.id.btn_crearFacultad)
        botonAnadirListView.setOnClickListener {
            irActividad(BCrearFacultad::class.java, REQUEST_CODE_ADD_OR_EDIT)
        }
        registerForContextMenu(listView)
    }
    //Funcion para moverse entre actividades, en este caso solo a Crear facultad
    private fun irActividad(clase: Class<*>, requestCode: Int) {
        val intent = Intent(this, clase)
        startActivityForResult(intent, requestCode)
    }

    // Maneja la informacion de las actifividades, cuando se añade una nueva Facultad
    // se actualiza la lista de Facultades de la actifidad pricipal
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_OR_EDIT && resultCode == RESULT_OK) {
            adaptador.notifyDataSetChanged() // Actualizar la informacion
        }
    }
    //Creacion del menu contextual.
    //El menu simplemente se instancia al seleccionar un elemento de la lista
    //Las opciones estan predefinidas en Elminar, Editar y Ver Materias

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu,v,menuInfo)
        // poblamos de informacion el menu, eliminar...
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Se obtiene el id del item seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        //se establece la posicion del elemento segun el elemento seleccionado
        posicionItemSeleccionado = posicion
    }

    //En el caso de selecicionar un item del menu...
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //En caso de editar...
            R.id.mi_editar -> {
                // Obtener La facultad seleccionado
                val facultadSeleccionada = arreglo[posicionItemSeleccionado]

                // Crear el Intent y pasar La facultad Al menu de creacion como informacion
                val intent = Intent(this, BCrearFacultad::class.java)
                intent.putExtra("Facultad", facultadSeleccionada)
                startActivityForResult(intent, REQUEST_CODE_ADD_OR_EDIT)
                adaptador.notifyDataSetChanged()
                true
            }
            //en caso de eliminar directamente se lo elimina del arreglo
            R.id.mi_eliminar -> {
                val facultadSeleccionada = arreglo[posicionItemSeleccionado]
                BBaseDatosMemoria.arregloBFacultad.remove(facultadSeleccionada)
                adaptador.notifyDataSetChanged()
                true
            }
            //En caso de editar Se encia como informacion añadida a la ventana de visualizacion
            //de materias La facultad BMATERIAS
            R.id.mi_verMaterias -> {
                val facultadSeleccionada = arreglo[posicionItemSeleccionado]
                // Crear el Intent y pasar el director como extra
                val intent = Intent(this, BMaterias::class.java)
                intent.putExtra("Facultad", facultadSeleccionada)
                startActivityForResult(intent, REQUEST_CODE_ADD_OR_EDIT)
                adaptador.notifyDataSetChanged()

                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    companion object {
        const val REQUEST_CODE_ADD_OR_EDIT = 1
    }
}