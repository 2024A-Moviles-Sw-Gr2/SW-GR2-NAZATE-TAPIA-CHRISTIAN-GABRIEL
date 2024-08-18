package com.msaasd.progresspro.features.profile

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.msaasd.progresspro.R
import com.msaasd.progresspro.features.history.HistoryActivity
import com.msaasd.progresspro.features.task.TaskActivity
import com.msaasd.progresspro.features.task.TaskViewModel

class ProfileActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Configura la barra de herramientas
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configura el cajón de navegación
        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        // Configura la barra de progreso
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.max = 100
        val targetProgress = 70 // Valor de progreso deseado (puede calcularse dinámicamente)

        // Crea y lanza una animación para la barra de progreso
        val animator = ObjectAnimator.ofInt(progressBar, "progress", targetProgress)
        animator.duration = 5000 // Duración de la animación (5 segundos)
        animator.start()

        // Configura el menú de navegación
        navView = findViewById(R.id.nav_view)
        navView.getHeaderView(0).findViewById<ImageButton>(R.id.imageProfileButtonNav).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Maneja las selecciones del menú de navegación
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_task -> {
                    val intent = Intent(this, TaskActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_history -> {
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                }
                else -> super.onContextItemSelected(menuItem)
            }
            // Cierra el cajón de navegación
            drawer.closeDrawers()
            true
        }
    }
}
