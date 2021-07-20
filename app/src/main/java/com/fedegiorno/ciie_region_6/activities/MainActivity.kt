package com.fedegiorno.ciie_region_6.activities

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.facebook.login.LoginManager
import com.fedegiorno.ciie_region_6.R
import com.fedegiorno.ciie_region_6.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val db = FirebaseFirestore.getInstance()

    lateinit var v: View

    /* BOTTOM NAVIGATION BAR */
    private lateinit var bottomNavViewMain : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment
    /* BOTTOM NAVIGATION BAR */

    private lateinit var nombreCurso: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup
        val bundle: Bundle? = intent.extras
        val name: String? = bundle?.getString("name")
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")

        setup(email ?: "", provider ?: "")

        //Guardamos datos de autenticacion
        val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        nombreCurso = ""
    }

    private fun setup(email: String, provider: String) {
        title = "Listado de Cursos"
    }

//    fun setNombreCurso(nombre: String){
//        nombreCurso = nombre
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.Item1 -> {//Informacion de los CIIE Region 6
                val infoCiieActivity = Intent(this, InfoCIIEActivity::class.java)
                startActivity(infoCiieActivity)
                true
            }
            R.id.Item2 -> {//Preferencias
                Toast.makeText(this,"Preferencias", Toast.LENGTH_SHORT).show()
                val preferencias: Intent = Intent(this, SettingsActivity::class.java)
                startActivity(preferencias)
                true
            }
            R.id.Item3 -> {//Formulario de inscripcion

                Toast.makeText(this, "Inscripcion", Toast.LENGTH_SHORT).show()
                val inscripcion: Intent = Intent(this, InscripcionActivity::class.java)
                startActivity(inscripcion)
                true

//                val ListIntent: Intent = Intent(this, MainActivity::class.java).apply {
//                    putExtra("email", email)
//                    putExtra("provider", provider.name)
//                }
            }
            R.id.Item4 -> {//Modificar datos docente

                Toast.makeText(this,"Modificar datos del docente", Toast.LENGTH_SHORT).show()
                val modificarDocente: Intent = Intent(this, RegisterActivity::class.java)
                startActivity(modificarDocente)
                true

            }
            R.id.Item5 -> {//Salir
                Toast.makeText(this,"Salir", Toast.LENGTH_SHORT).show()

                val prefs: SharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
                val provider: String = ""
                prefs.getString("provider", "")

                if (provider == ProviderType.FACEBOOK.name) {
                    LoginManager.getInstance().logOut()
                }

                //Borrado de datos de autenticacion
                val prefes: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                prefes.clear()
                prefes.apply()

                FirebaseAuth.getInstance().signOut()
                onBackPressed()

                true
            }
            else -> false
        }
    }
}