package com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor


import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.RestEngine
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.Usuario
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioAPIService
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioItem
import retrofit2.Call

class RegistroInteractor {

    fun validarUsuario(usu: String): Usuario? {
        val llamada : UsuarioAPIService =
            RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
        val resultado : Call<Usuario> = llamada.obtenerUsuario("bd.json")
        val u: Usuario? = resultado.execute().body()

        for (i in u!!){
            if (i.usuario == usu){
                return u
            }
        }
        return null
    }

    fun agregarUsuario(x : Int, usuarioItem: UsuarioItem) : UsuarioItem? {

            val llamada : UsuarioAPIService =
                RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
            val resultado : Call<UsuarioItem> = llamada.agregarUsuario(x,usuarioItem)
            val u: UsuarioItem? = resultado.execute().body()

            return u
    }

     fun cantidadRegistros(): Int {
        val llamada: UsuarioAPIService = RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
        val resultado: Call<Usuario> = llamada.obtenerUsuario("bd.json")
        val u: Usuario? = resultado.execute().body()
        return u!!.size
    }
}