package com.fedegiorno.ciie_region_6.entities

import android.os.Parcel
import android.os.Parcelable


class Docente (
    email: String,
    provider: String,
    apellido: String,
    nombres: String,
    dni: String,
    nacimiento: String,
    cargo: String,
    escuela: String,
    direccion: String,
    telefono: String): Parcelable {

    var email: String = email
    var provider: String = provider
    var apellido: String = apellido
    var nombres: String = nombres
    var dni: String = dni
    var nacimiento: String = nacimiento
    var cargo: String = cargo
    var escuela: String = escuela
    var direccion: String = direccion
    var telefono: String = telefono

    constructor(): this("","","","","","","","","","")

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
        writeString(email)
        writeString(provider)
        writeString(apellido)
        writeString(nombres)
        writeString(dni)
        writeString(nacimiento)
        writeString(cargo)
        writeString(escuela)
        writeString(direccion)
        writeString(telefono)
    }

    override fun toString(): String {
        return "Docente(email='$email', provider='$provider', apellido='$apellido', " +
                "nombres=$nombres, dni='$dni', nacimiento='$nacimiento', cargo='$cargo', " +
                "escuela=$escuela, direccion='$direccion', telefono='$telefono')"
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Docente> = object : Parcelable.Creator<Docente> {
            override fun createFromParcel(source: Parcel): Docente = Docente(source)
            override fun newArray(size: Int): Array<Docente?> = arrayOfNulls(size)
        }
    }

}