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
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentFragSportsBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.fragments.CategoriasViewModel


class FragSports : Fragment() {

    //Binding
    private lateinit var binding: FragmentFragSportsBinding

    //viewModel
    private lateinit var fragSportsViewModel: CategoriasViewModel

    //RecyclerView
    private lateinit var myRecyclerS: RecyclerView
    private lateinit var adaptador: NoticiasAdaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentFragSportsBinding.inflate(layoutInflater)

        //recycler
        myRecyclerS = binding.myRecyclerS
        myRecyclerS.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //viewmodel
        fragSportsViewModel = ViewModelProvider(this)[CategoriasViewModel::class.java]
        observer()

        binding.progressBar.visibility = View.VISIBLE
        fragSportsViewModel.btnGetCategory("sports", "es")

        return binding.root
    }

    private fun observer() {
        fragSportsViewModel.categories.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            adaptador = NoticiasAdaptador(requireContext().applicationContext, it.data)
            myRecyclerS.adapter = adaptador
        })
    }
}