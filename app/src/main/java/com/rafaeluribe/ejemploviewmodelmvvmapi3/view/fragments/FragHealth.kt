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
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentFragHealthBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.fragments.CategoriasViewModel


class FragHealth : Fragment() {

    //Binding
    private lateinit var binding: FragmentFragHealthBinding

    //viewModel
    private lateinit var fragHealthViewModel: CategoriasViewModel

    //RecyclerView
    private lateinit var myRecyclerH: RecyclerView
    private lateinit var adaptador: NoticiasAdaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFragHealthBinding.inflate(layoutInflater)

        //recycler
        myRecyclerH = binding.myRecyclerH
        myRecyclerH.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //viewmodel
        fragHealthViewModel = ViewModelProvider(this)[CategoriasViewModel::class.java]
        observer()

        binding.progressBar.visibility = View.VISIBLE
        fragHealthViewModel.btnGetCategory("health", "en")

        return binding.root
    }

    private fun observer() {
        fragHealthViewModel.categories.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            adaptador = NoticiasAdaptador(requireContext().applicationContext, it.data)
            myRecyclerH.adapter = adaptador
        })
    }
}