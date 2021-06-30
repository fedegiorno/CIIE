package com.fedegiorno.ciie_region_6.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.fedegiorno.ciie_region_6.R

enum class ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup
        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")

        Log.i("MIFIREBASE", "Email: $email")
        Log.i("MIFIREBASE", "Provider: $provider")

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

}