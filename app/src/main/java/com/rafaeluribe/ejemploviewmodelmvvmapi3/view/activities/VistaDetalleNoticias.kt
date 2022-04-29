package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityVistaDetalleNoticiasBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.Article

class VistaDetalleNoticias : AppCompatActivity() {

    //ViewBinding
    private lateinit var b: ActivityVistaDetalleNoticiasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityVistaDetalleNoticiasBinding.inflate(layoutInflater)
        setContentView(b.root)

        val text = intent.extras?.getString("articles")
        val news: Article = Gson().fromJson(text, Article::class.java)

        b.txtTitulo2.text = news.title
        b.txtNoticia2.text = news.description
    }
}