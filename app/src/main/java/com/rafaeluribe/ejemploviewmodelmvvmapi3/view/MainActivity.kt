package com.rafaeluribe.ejemploviewmodelmvvmapi3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityMainBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasTag
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var b : ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)


        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observar()

        b.btnTraerNoticias.setOnClickListener {
            b.progressBar.visibility = View.VISIBLE
            mainViewModel.onBtnTraerNoticias()
        }
    }

    private fun observar() {
        mainViewModel.noticias.observe(this, Observer {

            b.myRecycler.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

            var listRecycler: ArrayList<NoticiasTag> = ArrayList()

            for ((i) in it.articles.withIndex()){
                listRecycler.add(NoticiasTag(it.articles[i].title,
                                 it.articles[i].description, it.articles[i].urlToImage))
            }

            var adaptador = NoticiasAdaptador(applicationContext, listRecycler)
            b.myRecycler.adapter = adaptador
            b.progressBar.visibility = View.GONE
        })
    }
}