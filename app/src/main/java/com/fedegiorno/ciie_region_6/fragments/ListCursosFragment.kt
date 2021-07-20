package com.fedegiorno.ciie_region_6.fragments

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.findNavController
import com.fedegiorno.ciie_region_6.adapters.CursoListAdapter
import com.fedegiorno.ciie_region_6.entities.Curso
import com.fedegiorno.ciie_region_6.R
import com.fedegiorno.ciie_region_6.activities.MainActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.itextpdf.text.pdf.PdfName.VIEW
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class ListCursosFragment : Fragment() {

    companion object {
        fun newInstance() = ListCursosFragment()
    }

    private lateinit var viewModel: ListViewModel

    private lateinit var v: View
    private lateinit var recCursos: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var linearLayoutManager: LinearLayoutManager
    /* Es para definir el formato de la lista,
    en este caso sus elementos estarán uno debajo del otro */

    private lateinit var cursosListAdapter: CursoListAdapter

    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list_cursos, container, false)

        //Toast.makeText(v.context, "ListCursosFragment", Toast.LENGTH_SHORT).show()
        recCursos = v.findViewById(R.id.reclistcursos)

        recCursos.setHasFixedSize(true)
        recCursos.layoutManager = LinearLayoutManager(context)

        progressBar = v.findViewById(R.id.progressBar)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)


        // TODO: Use the ViewModel
    }


    override fun onStart() {
        super.onStart()

        var listaCursos: MutableList<Curso> = ArrayList<Curso>()
        val parentJob = Job()
        val scope = CoroutineScope(Dispatchers.Main + parentJob)

////        Completo una lista de cursos inicial
//        viewModel.initTestList()
//
////        Cargo la lista inicial en la DB
//        for (curso in viewModel.cursos) {
//            db.collection("cursos").add(curso)
//        }

        scope.launch{
            progressBar.visibility = View.VISIBLE
            //delay(5000)

            listaCursos = traerDatosDB(listaCursos)

            Log.i("completarRecycler", "listaCursos -> ${listaCursos.size}")
            cursosListAdapter = CursoListAdapter(listaCursos, requireContext()) { pos ->
                onItemClick(listaCursos, pos)
            }
            recCursos.adapter = cursosListAdapter
            //Se pasa el adaptador al recycler, se muestra en pantalla la lista

            progressBar.visibility = View.GONE
        }
    }

    private suspend fun traerDatosDB(listaCursos: MutableList<Curso>):MutableList<Curso> {

        val cursosRef = db.collection("cursos")
        val query = cursosRef

        try {
            val data = query.get().await()
            for (document in data) {
                //Log.i("MIFIREBASE", "${document.id} -> ${document.data}")
                listaCursos.add(document.toObject())
            }
        } catch (e: Exception) {

        }
        return listaCursos
    }

    private fun onItemClick(listaCursos: MutableList<Curso> ,position: Int): Boolean {

        var name: String = listaCursos[position].name.toString()
        var descripcion: String = listaCursos[position].descripcion
        var capacitador: String = listaCursos[position].capacitador
        var puntaje: String = listaCursos[position].puntaje
        var inicio: String = listaCursos[position].inicio
        var fin: String = listaCursos[position].fin
        var horario: String = listaCursos[position].horario
        var carga: String = listaCursos[position].carga
        var nivel: String = listaCursos[position].nivel
        var requisitos: String = listaCursos[position].requisitos


        val actionCurso = ListCursosFragmentDirections.actionListCursosFragmentToContainerFragment(
            name,
            descripcion,
            capacitador,
            puntaje,
            inicio,
            fin,
            horario,
            carga,
            nivel,
            requisitos)
        v.findNavController().navigate(actionCurso)

        return true
    }

}