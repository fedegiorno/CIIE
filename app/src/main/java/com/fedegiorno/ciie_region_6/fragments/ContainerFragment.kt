/*
Desarrollo de Aplicaciones para Dispositivos Moviles
TRABAJO PRACTICO FINAL

ContainerFragment.kt
Ubicacion: com.fedegiorno.ciie_region_6.fragments

Este es el fragmento que trabajara como contenedor de los fragmentos dedicados a mostrar los
detalles de la lista que estaran en tres pestañas
 */

package com.fedegiorno.ciie_region_6.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.fedegiorno.ciie_region_6.R
import com.fedegiorno.ciie_region_6.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

var _nombre: String = ""
var _descripcion: String = ""
var _capacitador: String = ""
var _puntaje: String = ""
var _inicio: String = ""
var _fin: String = ""
var _horario: String = ""
var _carga: String = ""
var _nivel: String = ""
var _requisitos: String = ""

class ContainerFragment : Fragment() {

    private lateinit var v: View
    private lateinit var vpgContainer: ViewPager2
    private lateinit var tabContainer: TabLayout

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_container, container, false)

        tabContainer = v.findViewById(R.id.tabContainer)

        vpgContainer = v.findViewById(R.id.vpgContainer)

        name = ContainerFragmentArgs.fromBundle(requireArguments()).name
        descripcion = ContainerFragmentArgs.fromBundle(requireArguments()).descripcion
        capacitador = ContainerFragmentArgs.fromBundle(requireArguments()).capacitador
        puntaje = ContainerFragmentArgs.fromBundle(requireArguments()).puntaje
        inicio = ContainerFragmentArgs.fromBundle(requireArguments()).inicio
        fin = ContainerFragmentArgs.fromBundle(requireArguments()).fin
        horario = ContainerFragmentArgs.fromBundle(requireArguments()).horario
        carga = ContainerFragmentArgs.fromBundle(requireArguments()).carga
        nivel = ContainerFragmentArgs.fromBundle(requireArguments()).nivel
        requisitos = ContainerFragmentArgs.fromBundle(requireArguments()).requisitos


        _nombre = name
        _descripcion = descripcion
        _capacitador = capacitador
        _puntaje = puntaje
        _inicio = inicio
        _fin = fin
        _horario = horario
        _carga = carga
        _nivel = nivel
        _requisitos = requisitos


        return v
    }

    override fun onStart() {
        super.onStart()

        vpgContainer.adapter = ViewPagerAdapter(requireActivity())

        TabLayoutMediator(tabContainer, vpgContainer, TabLayoutMediator.TabConfigurationStrategy
        { tab, position ->
            when (position) {
                0 -> tab.text = "Presentacion"
                1 -> tab.text = "Temario"
                2 -> tab.text = "Requisitos"
                else -> tab.text = "undefined"
            }
        }).attach()

        //Toast.makeText(v.context, "Container: $_capacitador", Toast.LENGTH_LONG).show()
        //Snackbar.make(v,"Nombre de curso: $name", Snackbar.LENGTH_SHORT).show()

    }
}