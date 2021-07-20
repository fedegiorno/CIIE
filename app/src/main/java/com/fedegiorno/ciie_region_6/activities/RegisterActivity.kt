package com.fedegiorno.ciie_region_6.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.fedegiorno.ciie_region_6.R
import com.fedegiorno.ciie_region_6.databinding.ActivityRegisterBinding
import com.fedegiorno.ciie_region_6.entities.Docente
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Formulario de Registro"

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

        binding.etxEmail.setText(emailIntent)
        binding.etxApellido.setText(apellido)
        binding.etxNombres.setText(nombres)
        binding.etxDNI.setText(dni)
        binding.etxNacimiento.setText(nacimiento)
        binding.etxDireccion.setText(direccion)
        binding.etxTelefono.setText(telefono)

        binding.spnCargo.adapter = adaptadorCargos
        binding.spnEscuela.adapter = adaptadorEscuelas

        var itemCargoSeleccionado: Int = 0

        for (i in 0 until binding.spnCargo.count){
            if (binding.spnCargo.getItemAtPosition(i) == cargo){
                itemCargoSeleccionado = i
            }
        }

        var itemEscuelaSeleccionada: Int = 0

        for (i in 0 until binding.spnEscuela.count){
            if (binding.spnEscuela.getItemAtPosition(i) == escuela){
                itemEscuelaSeleccionada = i
            }
        }

        binding.spnCargo.setSelection(itemCargoSeleccionado)
        binding.spnEscuela.setSelection(itemEscuelaSeleccionada)

        binding.etxNacimiento.setOnClickListener{showDatePickerDialog()}

        // ********* BOTON ACEPTAR ***********
        binding.btnAceptar.setOnClickListener{
            val db = FirebaseFirestore.getInstance()

            var docenteActual: Docente = Docente()

            docenteActual.email = binding.etxEmail.text.toString()
            docenteActual.provider = providerIntent.toString()
            docenteActual.apellido = binding.etxApellido.text.toString()
            docenteActual.nombres = binding.etxNombres.text.toString()
            docenteActual.dni = binding.etxDNI.text.toString()
            docenteActual.nacimiento = binding.etxNacimiento.text.toString()
            docenteActual.cargo = binding.spnCargo.getSelectedItem().toString()
            docenteActual.escuela = binding.spnEscuela.getSelectedItem().toString()
            docenteActual.direccion = binding.etxDireccion.text.toString()
            docenteActual.telefono = binding.etxTelefono.text.toString()

            db.collection("docentes").document(docenteActual.email).set(docenteActual)

            val listIntent: Intent = Intent(this, MainActivity::class.java).apply { }
            startActivity(listIntent)
        }

        // ********* BOTON CANCELAR ***********
        binding.btnCancel.setOnClickListener{

        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int){
        binding.etxNacimiento.setText("$day/$month/$year")
    }

}