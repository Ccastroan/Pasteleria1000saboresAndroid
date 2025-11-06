package com.example.android1000sabores.model

data class User(
    val correo: String = "",
    val clave: String = "",
    val nombre: String = "",
    val rol : String = ""//Variable local para controlar los roles de los correos
    )