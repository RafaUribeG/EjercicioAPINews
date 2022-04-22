package com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NoticiasAPIService {


        @Headers(
                value = [
                        "X-Api-Key: 4e74eb29eccf474eb57647823ecccba8",
                        "content-type: application/json; charset=utf-8"
                ]
        )
        @GET("everything?q=chile")
        fun obtenerNoticias(@Query("language") language: String): Call<Noticias>

        /*
        @GET("news?access_key=50e5ea75c7004e9a26afcf256d1dc590&countries=cl")
        fun buscarKeyword(@Query("keywords") keyword: String): Call<Noticias>

         */
}