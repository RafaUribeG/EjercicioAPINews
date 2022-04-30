package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityVistaDetalleNoticiasBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.Data

class VistaDetalleNoticias : AppCompatActivity() {

    //ViewBinding
    private lateinit var b: ActivityVistaDetalleNoticiasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityVistaDetalleNoticiasBinding.inflate(layoutInflater)
        setContentView(b.root)

        val text = intent.extras?.getString("data")
        val news: Data = Gson().fromJson(text, Data::class.java)

        b.myWebView.loadUrl(news.url)
        //b.txtTitulo2.text = news.title
        //b.txtNoticia2.text = news.description

        b.shareBtn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Dale un vistazo a esta noticia " + news.url)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Compartir por: "))
        }
    }
}