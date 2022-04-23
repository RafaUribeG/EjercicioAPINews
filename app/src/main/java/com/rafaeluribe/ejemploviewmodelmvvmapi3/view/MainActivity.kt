package com.rafaeluribe.ejemploviewmodelmvvmapi3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityMainBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var b : ActivityMainBinding

    //ViewModel
    private lateinit var mainViewModel: MainViewModel

    //RecyclerView
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var adaptador: NoticiasAdaptador


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        //ViewModel
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observar()

        //recycler
        myRecyclerView = b.myRecycler
        myRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        b.btnTraerNoticias.setOnClickListener {
            b.progressBar.visibility = View.VISIBLE
            mainViewModel.onBtnTraerNoticias()
        }
    }

    private fun observar() {
        mainViewModel.noticias.observe(this, Observer {

            adaptador = NoticiasAdaptador(applicationContext, it.articles)
            myRecyclerView.adapter = adaptador

        })
    }
}