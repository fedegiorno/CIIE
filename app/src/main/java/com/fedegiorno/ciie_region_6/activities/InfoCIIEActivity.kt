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
import com.fedegiorno.ciie_region_6.databinding.ActivityInfociieBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class InfoCIIEActivity : AppCompatActivity() {

    /* BOTTOM NAVIGATION BAR */
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment
    /* BOTTOM NAVIGATION BAR */

    private lateinit var binding: ActivityInfociieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfociieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* BOTTOM NAVIGATION BAR */
        //revisar el navHostFragment en relacion con el ViewBinding
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_infociie) as NavHostFragment
        bottomNavView = binding.bottomBar
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
        /* BOTTOM NAVIGATION BAR */
    }

    companion object


}