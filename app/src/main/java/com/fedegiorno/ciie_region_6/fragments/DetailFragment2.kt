/*
Desarrollo de Aplicaciones para Dispositivos Moviles
TRABAJO PRACTICO FINAL
Temas: BottomNavigationBars, NavigationDrawer, Tabs, Preferences y Splash

DetailFragment2.kt
Ubicacion: com.fedegiorno.parcial1.fragments

Este es el fragmento contendra el segundo grupo de detalles de la lista
 */

package com.fedegiorno.ciie_region_6.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fedegiorno.ciie_region_6.R
import android.widget.Toast

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment2 : Fragment() {

    private lateinit var v : View
    lateinit var name: String
    lateinit var descripcion: String

    lateinit var txtNombre2: TextView
    lateinit var txtDescripcion: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_detail2, container, false)
        txtNombre2 = v.findViewById(R.id.txtNombre2)
        txtDescripcion = v.findViewById(R.id.txtDescripcion)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey("name") }?.apply {
            _nombre = getString("name", _nombre)
        }
        arguments?.takeIf { it.containsKey("descripcion") }?.apply {
            _descripcion = getString("descripcion", _descripcion)
        }
    }

    override fun onStart() {
        super.onStart()

        val campo1: String = _nombre
        val campo2: String = _descripcion

        //Toast.makeText(v.context, "Detail: $campo1", Toast.LENGTH_LONG).show()

        txtNombre2.text = campo1
        txtDescripcion.text = campo2
    }

    companion object{
        var _nombre: String = ""
        var _descripcion: String = ""
    }

}