package com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor

import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.Noticias
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.NoticiasAPIService
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.RestEngine
import retrofit2.Call

class FragTotalNewsInteractor {

    fun traerRespuesta(categoria: String): Noticias?{
        val llamada: NoticiasAPIService =
            RestEngine.getRestEngine().create(NoticiasAPIService::class.java)
        val resultado: Call<Noticias> = llamada.obtenerNoticias(categoria)
        val p: Noticias? = resultado.execute().body()

        return p
    }

    fun traerKeyword(keyword: String):Noticias?{
        val llamada: NoticiasAPIService = RestEngine.getRestEngine().create(NoticiasAPIService::class.java)
        val resultado: Call<Noticias> = llamada.buscarPalabraClave(keyword)
        val p: Noticias? = resultado.execute().body()

        return p
    }

}