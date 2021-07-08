/*
Desarrollo de Aplicaciones para Dispositivos Moviles
TRABAJO PRACTICO FINAL
Temas: BottomNavigationBars, NavigationDrawer, Tabs, Preferences y Splash

DetailFragment1.kt
Ubicacion: com.fedegiorno.ciie_region_6.fragments

Este es el fragmento contendra el primer grupo de detalles de la lista
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
class DetailFragment1 : Fragment() {

    private lateinit var v : View
    lateinit var name: String
    lateinit var capacitador: String
    lateinit var puntaje: String
    lateinit var inicio: String
    lateinit var fin: String
    lateinit var horario: String

    lateinit var txtNombre: TextView
    lateinit var txtCapacitador: TextView
    lateinit var txtPuntaje: TextView
    lateinit var txtInicio: TextView
    lateinit var txtFin: TextView
    lateinit var txtHorario: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_detail1, container, false)
        txtNombre = v.findViewById(R.id.txtNombre)
        txtCapacitador = v.findViewById(R.id.txtCapacitador)
        txtPuntaje = v.findViewById(R.id.txtPuntaje)
        txtInicio = v.findViewById(R.id.txtInicio)
        txtFin = v.findViewById(R.id.txtFin)
        txtHorario = v.findViewById(R.id.txtHorario)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey("name") }?.apply {
            _nombre = getString("name", _nombre)
        }
        arguments?.takeIf { it.containsKey("capacitador") }?.apply {
            _capacitador = getString("capacitador", _capacitador)
        }
        arguments?.takeIf { it.containsKey("puntaje") }?.apply {
            _puntaje = getString("puntaje", _puntaje)
        }
        arguments?.takeIf { it.containsKey("inicio") }?.apply {
            _inicio = getString("inicio", _inicio)
        }
        arguments?.takeIf { it.containsKey("fin") }?.apply {
            _fin = getString("fin",_fin)
        }
        arguments?.takeIf { it.containsKey("horario") }?.apply {
            _horario = getString("horario", _horario)
        }
    }

    override fun onStart() {
        super.onStart()

        val campo1: String = _nombre
        val campo3: String = _capacitador
        val campo4: String = _puntaje
        val campo5: String = _inicio
        val campo6: String = _fin
        val campo7: String = _horario

        //Toast.makeText(v.context, "Detail: $campo1", Toast.LENGTH_LONG).show()

        txtNombre.text = campo1
        txtCapacitador.text = campo3
        txtPuntaje.text = campo4
        txtInicio.text = campo5
        txtFin.text = campo6
        txtHorario.text = campo7
    }

    companion object{
        var _nombre: String = ""
        var _capacitador: String = ""
        var _puntaje: String = ""
        var _inicio: String = ""
        var _fin: String = ""
        var _horario: String = ""
    }
}
