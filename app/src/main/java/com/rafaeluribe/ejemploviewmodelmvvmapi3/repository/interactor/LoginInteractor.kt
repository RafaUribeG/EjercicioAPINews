package com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor

import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.RestEngine
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.Usuario
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioAPIService
import retrofit2.Call

class LoginInteractor {

    fun validarUsuario(usu: String, pass: String): Usuario? {
        val llamada : UsuarioAPIService =
            RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
        val resultado : Call<Usuario> = llamada.obtenerUsuario("bd.json")
        val u: Usuario? = resultado.execute().body()

        for (i in u!!){
            if (i.usuario == usu && i.constrasena == pass){
                return u
            }
        }
        return null
    }
}