package com.fedegiorno.ciie_region_6.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.facebook.login.LoginManager
import com.fedegiorno.ciie_region_6.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup
        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")

//        Log.i("MIFIREBASE", "Email: $email")
//        Log.i("MIFIREBASE", "Provider: $provider")

        setup(email ?: "", provider ?: "")

        //Guardamos datos de autenticacion
        val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun setup(email: String, provider: String) {
        title = "Listado de Cursos"

        //Toast.makeText(this, "email: $email", Toast.LENGTH_LONG).show()
        //Toast.makeText(this, "provider: $provider", Toast.LENGTH_LONG).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
//            R.id.Item1 -> {
//                val InfoCiieActivity = Intent(this, InfoCIIEActivity::class.java)
//                startActivity(InfoCiieActivity)
//                true
//            }
//            R.id.Item2 -> {
//                Toast.makeText(this,"Preferencias", Toast.LENGTH_SHORT).show()
//                val preferencias: Intent = Intent(this, SettingsActivity::class.java)
//                startActivity(preferencias)
//                true
//            }
            R.id.Item3 -> {
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