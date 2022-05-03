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
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentFragScienceBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.fragments.CategoriasViewModel


class FragScience : Fragment() {


    //Binding
    private lateinit var binding: FragmentFragScienceBinding

    //viewModel
    private lateinit var fragScienceViewModel: CategoriasViewModel

    //RecyclerView
    private lateinit var myRecyclerSc: RecyclerView
    private lateinit var adaptador: NoticiasAdaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFragScienceBinding.inflate(layoutInflater)

        //recycler
        myRecyclerSc = binding.myRecyclerSc
        myRecyclerSc.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //viewmodel
        fragScienceViewModel = ViewModelProvider(this)[CategoriasViewModel::class.java]
        observer()

        binding.progressBar.visibility = View.VISIBLE
        fragScienceViewModel.btnGetCategory("science", "en")

        return binding.root
    }

    private fun observer() {
        fragScienceViewModel.categories.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            adaptador = NoticiasAdaptador(requireContext().applicationContext, it.data)
            myRecyclerSc.adapter = adaptador
        })
    }
}