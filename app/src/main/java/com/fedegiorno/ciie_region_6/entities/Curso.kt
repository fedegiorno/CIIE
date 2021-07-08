package com.fedegiorno.ciie_region_6.entities

import android.os.Parcel
import android.os.Parcelable

class Curso( name: String,
             descripcion: String,
             profesor: String,
             puntaje: String,
             inicio: String,
             fin: String,
             horario: String,
             carga: String,
             nivel: String,
             requisitos: String): Parcelable {

    var name: String
    var descripcion: String
    var profesor: String
    var puntaje: String
    var inicio: String
    var fin: String
    var horario: String
    var carga: String
    var nivel: String
    var requisitos: String

    constructor() : this("", "", "", "", "", "", "", "", "", "")

    init {
        this.name = name
        this.descripcion = descripcion
        this.profesor = profesor
        this.puntaje = puntaje
        this.inicio = inicio
        this.fin = fin
        this.horario = horario
        this.carga = carga
        this.nivel = nivel
        this.requisitos = requisitos
    }

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(descripcion)
        parcel.writeString(profesor)
        parcel.writeString(puntaje)
        parcel.writeString(inicio)
        parcel.writeString(fin)
        parcel.writeString(horario)
        parcel.writeString(carga)
        parcel.writeString(nivel)
        parcel.writeString(requisitos)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Curso(name='$name', descripcion='$descripcion', profesor=$profesor, " +
                "puntaje='$puntaje', inicio='$inicio', fin='$fin', horario=$horario, " +
                "carga ='$carga', nivel='$nivel', requisitos=$requisitos)"
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Curso> = object : Parcelable.Creator<Curso> {
            override fun createFromParcel(source: Parcel): Curso = Curso(source)
            override fun newArray(size: Int): Array<Curso?> = arrayOfNulls(size)
        }
    }
}
