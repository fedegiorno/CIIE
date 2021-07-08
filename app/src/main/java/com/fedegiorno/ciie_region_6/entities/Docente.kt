package com.fedegiorno.ciie_region_6.entities


class Docente (
    dni: String,
    apellido: String,
    nombres: String,
    usuario: String,
    email: String,
    password: String) {

    var dni: String = dni

    var apellido: String

    var nombres: String

    var usuario: String

    var email: String

    var password: String


    init {
        this.dni = dni
        this.apellido = apellido
        this.nombres = nombres
        this.usuario = usuario
        this.email = email
        this.password = password
    }
}