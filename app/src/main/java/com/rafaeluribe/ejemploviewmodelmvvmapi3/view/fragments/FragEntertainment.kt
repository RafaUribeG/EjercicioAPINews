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
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentFragEntertainmentBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentFragSportsBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.fragments.CategoriasViewModel


class FragEntertainment : Fragment() {

    //Binding
    private lateinit var binding: FragmentFragEntertainmentBinding

    //viewModel
    private lateinit var fragEntertainmentViewModel: CategoriasViewModel

    //RecyclerView
    private lateinit var myRecyclerE: RecyclerView
    private lateinit var adaptador: NoticiasAdaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFragEntertainmentBinding.inflate(layoutInflater)

        //recycler
        myRecyclerE = binding.myRecyclerE
        myRecyclerE.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //viewmodel
        fragEntertainmentViewModel = ViewModelProvider(this)[CategoriasViewModel::class.java]
        observer()

        binding.progressBar.visibility = View.VISIBLE
        fragEntertainmentViewModel.btnGetCategory("entertainment", "es")

        return binding.root
    }

    private fun observer() {
        fragEntertainmentViewModel.categories.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            adaptador = NoticiasAdaptador(requireContext().applicationContext, it.data)
            myRecyclerE.adapter = adaptador
        })
    }
}