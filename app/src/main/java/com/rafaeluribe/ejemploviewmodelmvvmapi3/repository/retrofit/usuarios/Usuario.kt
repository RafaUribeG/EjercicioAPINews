package com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios

class Usuario : ArrayList<UsuarioItem>()

data class UsuarioItem(
    val usuario: String,
    val constrasena: String,
    val nombre: String,
)