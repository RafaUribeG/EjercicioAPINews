package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.FragmentTotalNewsBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview.NoticiasAdaptador
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.fragments.TotalNewsViewModel


class FragmentoTotalNews : Fragment(), SearchView.OnQueryTextListener{

    //ViewBinding
    private lateinit var b : FragmentTotalNewsBinding

    //ViewModel
    private lateinit var fragTotalNewsViewModel: TotalNewsViewModel

    //RecyclerView
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var adaptador: NoticiasAdaptador

    //SearchView
    private lateinit var searchView :SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        b = FragmentTotalNewsBinding.inflate(layoutInflater)

        //ViewModel
        fragTotalNewsViewModel = ViewModelProvider(this)[TotalNewsViewModel::class.java]
        observar()

        //recycler
        myRecyclerView = b.myRecycler
        myRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        b.progressBar.visibility = View.VISIBLE
        fragTotalNewsViewModel.onBtnTraerNoticias()

        b.btnBuscarNoticia?.setOnClickListener {
            //b.progressBar.visibility = View.VISIBLE
            fragTotalNewsViewModel.onFindKeyword(b.textView.toString())
        }


        return b.root
    }

        private fun observar() {
            fragTotalNewsViewModel.noticias.observe(viewLifecycleOwner, Observer {
                b.progressBar.visibility = View.GONE
                adaptador = NoticiasAdaptador(requireContext().applicationContext, it.data)
                myRecyclerView.adapter = adaptador
            })
        }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }


}
