package com.fedegiorno.ciie_region_6.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.fedegiorno.ciie_region_6.R
import com.fedegiorno.ciie_region_6.entities.Curso
import com.google.android.material.textfield.TextInputEditText

class DbCursosFragmentModificar : Fragment() {

    private lateinit var v: View
    private lateinit var btnCancelarModificar: Button
    private lateinit var btnAceptarModificar: Button

//    private var db: appDatabase? = null
//    private var cursoDao: cursoDao? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_db_cursos_modificar, container, false)

        btnCancelarModificar = v.findViewById(R.id.btnCancelarModificar)
        btnAceptarModificar = v.findViewById(R.id.btnAceptarModificar)

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

        return v
    }

    override fun onStart() {
        super.onStart()
        //completar las vistas con los datos del curso seleccionado

        btnAceptarModificar.setOnClickListener {
//            cursoDao?.updateCurso(
//                Curso(id = cursoDao?.cuenta() + 1,
//                    name = tieName.text.toString(),
//                    descripcion = tieDescripcion.text.toString(),
//                    profesor = tieCapacitador.text.toString(),
//                    puntaje = tiePuntaje.text.toString(),
//                    inicio = tieInicio.text.toString(),
//                    fin =  tieFin.text.toString(),
//                    horario =  tieHorario.text.toString(),
//                    carga =  tieCarga.text.toString(),
//                    nivel =  tieNivel.text.toString(),
//                    requisitos = tieRequisitos.text.toString())
//            )
        }

        btnCancelarModificar.setOnClickListener {
            tieName.setText("")
            tieDescripcion.setText("")
            tieCapacitador.setText("")
            tiePuntaje.setText("")
            tieInicio.setText("")
            tieFin.setText("")
            tieHorario.setText("")
            tieCarga.setText("")
            tieNivel.setText("")
            tieRequisitos.setText("")
        }
    }
}