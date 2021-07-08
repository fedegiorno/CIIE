package com.fedegiorno.ciie_region_6.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fedegiorno.ciie_region_6.R
import com.fedegiorno.ciie_region_6.entities.Curso
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.fedegiorno.ciie_region_6.adapters.CursoListAdapter
import com.fedegiorno.ciie_region_6.fragments.ListViewModel

import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ListCursosFragment : Fragment() {

    companion object {
        fun newInstance() = ListCursosFragment()
    }

    private lateinit var viewModel: ListViewModel

    private lateinit var v: View
    private lateinit var recCursos: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    //var listaCursos: MutableList<Curso> = arrayListOf()
    var listaCursos: MutableList<Curso> = ArrayList<Curso>()
    private lateinit var cursosListAdapter: CursoListAdapter

    //private lateinit var adapterFirestoreRecycler: FirestoreRecyclerAdapter<Curso, CursoListAdapter.CursoHolder>

    // Access a Cloud Firestore instance from your Activity
    //private val db = FirebaseFirestore.getInstance()

    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore
    //private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

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

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)


        // TODO: Use the ViewModel
    }


    override fun onStart() {
        super.onStart()

////        Completo una lista de cursos inicial
//        viewModel.initTestList()
//
////        Cargo la lista inicial en la DB
//        for (curso in viewModel.cursos) {
//            db.collection("cursos").add(curso)
//        }

        //fillRecycler()
        traerDatosDB()

    }

    private fun traerDatosDB() {

        val cursosRef = db.collection("cursos")

        cursosRef
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //Log.i("MIFIREBASE", "${document.id} -> ${document.data}")
                    listaCursos.add(document.toObject())
                }
                Log.i("MIFIREBASE", "listaCursos -> ${listaCursos.size.toString()}")
                completarRecycler()
            }
            .addOnFailureListener { exception ->
                //Log.i("MIFIREBASE", "exception -> $exception")
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

    private fun completarRecycler() {

        // Configuracion del recyclerview
        recCursos.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(context)
        recCursos.layoutManager = linearLayoutManager

        cursosListAdapter = CursoListAdapter(listaCursos, requireContext()) { pos ->
            onItemClick(pos)
        }

        recCursos.adapter = cursosListAdapter
        //Se pasa el adaptador al recycler, se muestra en pantalla la lista
    }


//    private fun fillRecycler() {
//        val rootRef = FirebaseFirestore.getInstance()
//        val query = rootRef.collection("cursos")
//
//        val options = FirestoreRecyclerOptions.Builder<Curso>()
//            .setQuery(query, Curso::class.java)
//            .build()
//
//        adapterFirestoreRecycler = object :
//            FirestoreRecyclerAdapter<Curso, CursoListAdapter.CursoHolder>(options) {
//
//            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoListAdapter.CursoHolder {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.item_curso, parent, false)
//                return CursoListAdapter.CursoHolder(view)
//            }
//
//            override fun onBindViewHolder(holder: CursoListAdapter.CursoHolder, position: Int, model: Curso) {
//                holder.setName(model.name)
//            }
//
//            override fun onDataChanged() {
//                super.onDataChanged()
//            }
//        }
//        adapterFirestoreRecycler.startListening()
//        recCursos.adapter = adapterFirestoreRecycler
//    }

    private fun onItemClick(position: Int): Boolean {

        var name: String = listaCursos[position].name.toString()
        var descripcion: String = listaCursos[position].descripcion
        var profesor: String = listaCursos[position].profesor
        var puntaje: String = listaCursos[position].puntaje
        var inicio: String = listaCursos[position].inicio
        var fin: String = listaCursos[position].fin
        var horario: String = listaCursos[position].horario
        var carga: String = listaCursos[position].carga
        var nivel: String = listaCursos[position].nivel
        var requisitos: String = listaCursos[position].requisitos

        return true
    }

}