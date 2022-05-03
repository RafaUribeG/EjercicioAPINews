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
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentFragBusinessBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.fragments.CategoriasViewModel


class FragBusiness : Fragment() {

    //Binding
    private lateinit var binding: FragmentFragBusinessBinding

    //ViewModel
    private lateinit var fragBusinessViewModel : CategoriasViewModel


    //RecyclerView
    private lateinit var myRecyclerViewB: RecyclerView
    private lateinit var adaptador: NoticiasAdaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragBusinessBinding.inflate(layoutInflater)

        //ViewModel
        fragBusinessViewModel = ViewModelProvider(this).get(CategoriasViewModel::class.java)
        observer()

        //recycler
        myRecyclerViewB = binding.myRecyclerB
        myRecyclerViewB.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.progressBar.visibility = View.VISIBLE
        fragBusinessViewModel.btnGetCategory("business", "es")

        return binding.root
    }

    private fun observer() {
        fragBusinessViewModel.categories.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            adaptador = NoticiasAdaptador(requireContext().applicationContext, it.data)
            myRecyclerViewB.adapter = adaptador
        })
    }
}