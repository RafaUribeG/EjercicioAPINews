package com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor

import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.Noticias
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.NoticiasAPIService
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.RestEngine
import retrofit2.Call


class NoticiasInteractor {

    fun traerRespuesta(language: String): Noticias?{
        val llamada: NoticiasAPIService =
            RestEngine.getRestEngine().create(NoticiasAPIService::class.java)
        val resultado: Call<Noticias> = llamada.obtenerNoticias(language)
        val p:Noticias? = resultado.execute().body()

        return p
    }

    /*
    fun traerKeyword(keyword: String): Noticias?{
        val llamada: NoticiasAPIService =
            RestEngine.getRestEngine().create(NoticiasAPIService::class.java)
        val resultado: Call<Noticias> = llamada.buscarKeyword(keyword)
        val p:Noticias? = resultado.execute().body()

        return p
    }

     */
}