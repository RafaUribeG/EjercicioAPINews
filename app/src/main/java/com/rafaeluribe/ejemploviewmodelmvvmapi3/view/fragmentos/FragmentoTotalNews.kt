package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaeluribe.ejemploviewmodelmvvmapi3.R
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentTotalNewsBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.MainViewModel


class FragmentoTotalNews : Fragment() {

    //ViewBinding
    private lateinit var b : FragmentTotalNewsBinding

    //ViewModel
    //ViewModel
    private lateinit var mainViewModel: MainViewModel

    //RecyclerView
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var adaptador: NoticiasAdaptador


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        b = FragmentTotalNewsBinding.inflate(layoutInflater)

        //ViewModel
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observar()

        //recycler
        myRecyclerView = b.myRecycler
        myRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        b.btnTraerNoticias.setOnClickListener {
            b.progressBar.visibility = View.VISIBLE
            mainViewModel.onBtnTraerNoticias()
        }

        return b.root


    }

        private fun observar() {
            mainViewModel.noticias.observe(viewLifecycleOwner, Observer {

                adaptador = NoticiasAdaptador(requireContext(), it.articles)
                myRecyclerView.adapter = adaptador

            })
        }

}
