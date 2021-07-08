/*
Desarrollo de Aplicaciones para Dispositivos Moviles
TRABAJO PRACTICO FINAL

DbCursosFragmentAlta.kt
Ubicacion: com.fedegiorno.ciie_region_6.fragments

Este fragmento mostrara lo correspondiente a la primera division del Nav Bar
 */

package com.fedegiorno.ciie_region_6.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.fedegiorno.ciie_region_6.R
import com.fedegiorno.ciie_region_6.entities.Curso
import com.google.android.material.textfield.TextInputEditText

class DbCursosFragmentAlta : Fragment() {

    private lateinit var v: View
    
    private lateinit var btnCancelarAlta: Button
    private lateinit var btnAceptarAlta: Button

    var identificador: Int = 0
    lateinit var name: String
    lateinit var descripcion: String
    lateinit var capacitador: String
    lateinit var puntaje: String
    lateinit var inicio: String
    lateinit var fin: String
    lateinit var horario: String
    lateinit var carga: String
    lateinit var nivel: String
    lateinit var requisitos: String

    lateinit var tieName: TextInputEditText
    lateinit var tieDescripcion: TextInputEditText
    lateinit var tieCapacitador: TextInputEditText
    lateinit var tiePuntaje: TextInputEditText
    lateinit var tieInicio: TextInputEditText
    lateinit var tieFin: TextInputEditText
    lateinit var tieHorario: TextInputEditText
    lateinit var tieCarga: TextInputEditText
    lateinit var tieNivel: TextInputEditText
    lateinit var tieRequisitos: TextInputEditText

    lateinit var fontSize: String
    lateinit var txtAltaCursos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_db_cursos_alta, container, false)

        btnCancelarAlta = v.findViewById(R.id.btnCancelarAlta)
        btnAceptarAlta = v.findViewById(R.id.btnAceptarAlta)
        
        tieName = v.findViewById(R.id.tieName)
        tieDescripcion = v.findViewById(R.id.tieDescripcion)
        tieCapacitador = v.findViewById(R.id.tieCapacitador)
        tiePuntaje = v.findViewById(R.id.tiePuntaje)
        tieInicio = v.findViewById(R.id.tieInicio)
        tieFin = v.findViewById(R.id.tieFin)
        tieHorario = v.findViewById(R.id.tieHorario)
        tieCarga = v.findViewById(R.id.tieCarga)
        tieNivel = v.findViewById(R.id.tieNivel)
        tieRequisitos = v.findViewById(R.id.tieRequisitos)

        txtAltaCursos = v.findViewById(R.id.txtAltaCursos)

        return v
    }

    override fun onStart() {
        super.onStart()

//        var db: appDatabase? = null
//        var cursoDao: cursoDao? = null
//        val Prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        //Instancio el objeto DB
//        db = appDatabase.getAppDataBase(v.context)

        //Instancio la interface a la DB
//        cursoDao = db?.cursoDao()

//        fontSize = Prefs.getString("FontSize", "").toString()
//        txtAltaCursos.textSize = fontSize.toFloat()

//        btnAceptarAlta.setOnClickListener {
//            cursoDao?.insertCurso(
//                Curso(  id = cursoDao?.cuenta() + 1,
//                        name = tieName.text.toString(),
//                        descripcion = tieDescripcion.text.toString(),
//                        profesor = tieCapacitador.text.toString(),
//                        puntaje = tiePuntaje.text.toString(),
//                        inicio = tieInicio.text.toString(),
//                        fin =  tieFin.text.toString(),
//                        horario =  tieHorario.text.toString(),
//                        carga =  tieCarga.text.toString(),
//                        nivel =  tieNivel.text.toString(),
//                        requisitos = tieRequisitos.text.toString()))
//        }
//
//        btnCancelarAlta.setOnClickListener {
//            tieName.setText("")
//            tieDescripcion.setText("")
//            tieCapacitador.setText("")
//            tiePuntaje.setText("")
//            tieInicio.setText("")
//            tieFin.setText("")
//            tieHorario.setText("")
//            tieCarga.setText("")
//            tieNivel.setText("")
//            tieRequisitos.setText("")
//        }
    }
}