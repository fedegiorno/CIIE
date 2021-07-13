/*
Desarrollo de Aplicaciones para Dispositivos Moviles
TRABAJO PRACTICO FINAL
Temas: BottomNavigationBars, NavigationDrawer, Tabs, Preferences y Splash

BottomNavbarFragment1
Ubicacion: com.fedegiorno.ciie_region_6.fragments

Este fragmento mostrara lo correspondiente a la primera division del Nav Bar
 */

package com.fedegiorno.ciie_region_6.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fedegiorno.ciie_region_6.R

class BottomNavbarFragment1 : Fragment() {

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
        return inflater.inflate(R.layout.fragment_bottom_navbar1, container, false)
    }
}