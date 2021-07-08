/*
Desarrollo de Aplicaciones para Dispositivos Moviles
TRABAJO PRACTICO FINAL
Temas: BottomNavigationBars, NavigationDrawer, Tabs, Preferences y Splash

DetailFragment3.kt
Ubicacion: com.fedegiorno.parcial1.fragments

Este es el fragmento contendra el tercer grupo de detalles de la lista
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
class DetailFragment3 : Fragment() {

    private lateinit var v : View
    lateinit var name: String
    lateinit var requisitos: String

    lateinit var txtNombre3: TextView
    lateinit var txtRequisitos: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_detail3, container, false)
        txtNombre3 = v.findViewById(R.id.txtNombre3)
        txtRequisitos = v.findViewById(R.id.txtRequisitos)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey("name") }?.apply {
            _nombre = getString("name", _nombre)
        }
        arguments?.takeIf { it.containsKey("requisitos") }?.apply {
            _requisitos = getString("requisitos", _requisitos)
        }
    }

    override fun onStart() {
        super.onStart()

        val campo1: String = _nombre
        val campo8: String = _requisitos

        //Toast.makeText(v.context, "Detail: $campo1", Toast.LENGTH_LONG).show()

        txtNombre3.text = campo1
        txtRequisitos.text = campo8
    }

    companion object{
        var _nombre: String = ""
        var _requisitos: String = ""
    }

}