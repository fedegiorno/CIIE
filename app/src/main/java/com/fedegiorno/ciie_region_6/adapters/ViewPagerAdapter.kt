package com.fedegiorno.ciie_region_6.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fedegiorno.ciie_region_6.fragments.*


class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {

        val fragment1 = DetailFragment1()
        fragment1.arguments = Bundle().apply {
            putString("name", _nombre)
            putString("descripcion", _descripcion)
            putString("capacitador", _capacitador)
            putString("puntaje", _puntaje)
            putString("inicio", _inicio)
            putString("fin", _fin)
            putString("horario", _horario)
            putString("requisitos", _requisitos)
        }

        val fragment2 = DetailFragment2()
        fragment2.arguments = Bundle().apply {
            putString("name", _nombre)
            putString("descripcion", _descripcion)
        }

        val fragment3 = DetailFragment3()
        fragment3.arguments = Bundle().apply {
            putString("name", _nombre)
            putString("requisitos", _requisitos)
        }

        return when (position) {
            0 -> fragment1
            1 -> fragment2
            2 -> fragment3

            else -> fragment1
        }
    }

    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    companion object {
        private const val TAB_COUNT = 3
    }
}