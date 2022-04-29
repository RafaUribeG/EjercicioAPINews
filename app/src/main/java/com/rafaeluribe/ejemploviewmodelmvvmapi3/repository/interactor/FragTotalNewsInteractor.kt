package com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor

import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.Noticias
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.NoticiasAPIService
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.RestEngine
import retrofit2.Call

class FragTotalNewsInteractor {

    fun traerRespuesta(language: String): Noticias?{
        val llamada: NoticiasAPIService =
            RestEngine.getRestEngine().create(NoticiasAPIService::class.java)
        val resultado: Call<Noticias> = llamada.obtenerNoticias(language)
        val p: Noticias? = resultado.execute().body()

        return p
    }

}