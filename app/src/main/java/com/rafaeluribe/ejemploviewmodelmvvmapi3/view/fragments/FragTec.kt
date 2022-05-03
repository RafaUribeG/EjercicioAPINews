package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.fragments

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
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentFragScienceBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentFragTecBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.fragments.CategoriasViewModel


class FragTec : Fragment() {

    //Binding
    private lateinit var binding: FragmentFragTecBinding

    //viewModel
    private lateinit var fragTecViewModel: CategoriasViewModel

    //RecyclerView
    private lateinit var myRecyclerT: RecyclerView
    private lateinit var adaptador: NoticiasAdaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFragTecBinding.inflate(layoutInflater)

        //recycler
        myRecyclerT = binding.myRecyclerT
        myRecyclerT.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //viewmodel
        fragTecViewModel = ViewModelProvider(this)[CategoriasViewModel::class.java]
        observer()

        binding.progressBar.visibility = View.VISIBLE
        fragTecViewModel.btnGetCategory("technology", "es")

        return binding.root
    }

    private fun observer() {
        fragTecViewModel.categories.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            adaptador = NoticiasAdaptador(requireContext().applicationContext, it.data)
            myRecyclerT.adapter = adaptador
        })
    }
}