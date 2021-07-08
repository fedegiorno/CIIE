/*
Desarrollo de Aplicaciones para Dispositivos Moviles
TRABAJO PRACTICO FINAL
Temas: BottomNavigationBars, NavigationDrawer, Tabs, Preferences y Splash

InfoCIIEActivity.kt
Ubicacion: com.fedegiorno.ciie_region_6.activities

Esta activity mostrara el uso de NavigationDrawer
 */

package com.fedegiorno.ciie_region_6.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.fedegiorno.ciie_region_6.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class InfoCIIEActivity : AppCompatActivity() {

    /* BOTTOM NAVIGATION BAR */
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment
    /* BOTTOM NAVIGATION BAR */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infociie)

        /* BOTTOM NAVIGATION BAR */
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_infociie) as NavHostFragment
        bottomNavView = findViewById(R.id.bottom_bar)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
        /* BOTTOM NAVIGATION BAR */

    }

    companion object


}