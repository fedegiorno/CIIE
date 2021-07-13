package com.fedegiorno.ciie_region_6.entities

import android.os.Parcel
import android.os.Parcelable

class Curso(name: String,
            descripcion: String,
            capacitador: String,
            puntaje: String,
            inicio: String,
            fin: String,
            horario: String,
            carga: String,
            nivel: String,
            requisitos: String): Parcelable {

    var name: String = name
    var descripcion: String = descripcion
    var capacitador: String = capacitador
    var puntaje: String = puntaje
    var inicio: String = inicio
    var fin: String = fin
    var horario: String = horario
    var carga: String = carga
    var nivel: String = nivel
    var requisitos: String = requisitos

    constructor() : this("","","","","","","","","","")


    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )
    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(descripcion)
        writeString(capacitador)
        writeString(puntaje)
        writeString(inicio)
        writeString(fin)
        writeString(horario)
        writeString(carga)
        writeString(nivel)
        writeString(requisitos)
    }

    override fun toString(): String {
        return "Curso(name='$name', descripcion='$descripcion', capacitador='$capacitador', " +
                "puntaje=$puntaje, inicio='$inicio', fin='$fin', horario='$horario', " +
                "carga=$carga, nivel='$nivel', requisitos='$requisitos')"
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Curso> = object : Parcelable.Creator<Curso> {
            override fun createFromParcel(source: Parcel): Curso = Curso(source)
            override fun newArray(size: Int): Array<Curso?> = arrayOfNulls(size)
        }
    }
}
