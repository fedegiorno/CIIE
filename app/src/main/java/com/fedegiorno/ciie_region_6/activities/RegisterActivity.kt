package com.fedegiorno.ciie_region_6.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.core.view.forEach
import com.fedegiorno.ciie_region_6.R
import com.fedegiorno.ciie_region_6.entities.Docente
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        title = "Formulario de Registro"

        var etxEmail =findViewById<EditText>(R.id.etxEmail)
        var etxApellido =findViewById<EditText>(R.id.etxApellido)
        var etxNombres =findViewById<EditText>(R.id.etxNombres)
        var etxDNI =findViewById<EditText>(R.id.etxDNI)
        var etxNacimiento = findViewById<EditText>(R.id.etxNacimiento)
        var spnCargo =findViewById<Spinner>(R.id.spnCargo)
        var spnEscuela =findViewById<Spinner>(R.id.spnEscuela)
        var etxDireccion = findViewById<EditText>(R.id.etxDireccion)
        var etxTelefono = findViewById<EditText>(R.id.etxTelefono)

        val btnAceptar = findViewById<Button>(R.id.btnAceptar)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        val cargos = resources.getStringArray(R.array.cargos)
        val escuelas = resources.getStringArray(R.array.escuelas)

        val adaptadorCargos = ArrayAdapter(this, android.R.layout.simple_spinner_item, cargos)
        val adaptadorEscuelas = ArrayAdapter(this, android.R.layout.simple_spinner_item, escuelas)

        val prefs: SharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email: String? = prefs.getString("email", null)
        val provider: String? = prefs.getString("provider", null)

        val bundle: Bundle? = intent.extras
        var emailIntent: String? = bundle?.getString("email")
        var providerIntent: String? = bundle?.getString("provider")
        var apellido: String? = bundle?.getString("apellido")
        var nombres: String? = bundle?.getString("nombres")
        var dni: String? = bundle?.getString("dni")
        var nacimiento: String? = bundle?.getString("nacimiento")
        var cargo: String? = bundle?.getString("cargo")
        var escuela: String? = bundle?.getString("escuela")
        var direccion: String? = bundle?.getString("direccion")
        var telefono: String? = bundle?.getString("telefono")

        etxEmail.setText(emailIntent)
        etxApellido.setText(apellido)
        etxNombres.setText(nombres)
        etxDNI.setText(dni)
        etxNacimiento.setText(nacimiento)
        etxDireccion.setText(direccion)
        etxTelefono.setText(telefono)

        spnCargo.adapter = adaptadorCargos
        spnEscuela.adapter = adaptadorEscuelas

        var itemCargoSeleccionado: Int = 0

        for (i in 0 until spnCargo.count){
            if (spnCargo.getItemAtPosition(i) == cargo){
                itemCargoSeleccionado = i
            }
        }

        var itemEscuelaSeleccionada: Int = 0

        for (i in 0 until spnEscuela.count){
            if (spnEscuela.getItemAtPosition(i) == escuela){
                itemEscuelaSeleccionada = i
            }
        }

        spnCargo.setSelection(itemCargoSeleccionado)
        spnEscuela.setSelection(itemEscuelaSeleccionada)

        etxNacimiento.setOnClickListener{showDatePickerDialog()}

        // ********* BOTON ACEPTAR ***********
        btnAceptar.setOnClickListener{
            val db = FirebaseFirestore.getInstance()

            var docenteActual: Docente = Docente()

            docenteActual.email = etxEmail.text.toString()
            docenteActual.provider = providerIntent.toString()
            docenteActual.apellido = etxApellido.text.toString()
            docenteActual.nombres = etxNombres.text.toString()
            docenteActual.dni = etxDNI.text.toString()
            docenteActual.nacimiento = etxNacimiento.text.toString()
            docenteActual.cargo = spnCargo.getSelectedItem().toString()
            docenteActual.escuela = spnEscuela.getSelectedItem().toString()
            docenteActual.direccion = etxDireccion.text.toString()
            docenteActual.telefono = etxTelefono.text.toString()

            db.collection("docentes").document(docenteActual.email).set(docenteActual)

            val listIntent: Intent = Intent(this, MainActivity::class.java).apply { }
            startActivity(listIntent)
        }

        // ********* BOTON CANCELAR ***********
        btnCancel.setOnClickListener{

        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int){
        etxNacimiento.setText("$day/$month/$year")
    }

}