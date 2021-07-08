package com.fedegiorno.ciie_region_6.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.fedegiorno.ciie_region_6.R

class DbCursosFragmentEliminar : Fragment() {

    private lateinit var v: View
    private lateinit var btnCancelarEliminar: Button
    private lateinit var btnAceptarEliminar: Button

    var identificador: Int = 0

//    private var db: appDatabase? = null
//    private var cursoDao: cursoDao? = null

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
        v = inflater.inflate(R.layout.fragment_db_cursos_eliminar, container, false)


        Toast.makeText(context, identificador.toString(), Toast.LENGTH_SHORT).show()
        //Toast.makeText(context, "ELIMINAR", Toast.LENGTH_SHORT).show()
        //Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()


        btnCancelarEliminar = v.findViewById(R.id.btnCancelarEliminar)
        btnAceptarEliminar = v.findViewById(R.id.btnAceptarEliminar)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnAceptarEliminar.setOnClickListener {
            //Borrar Curso de la Base de datos

        }

        btnCancelarEliminar.setOnClickListener {
            //Actualizar Curso de la Base de datos

        }
    }
}