package com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor.NoticiasInteractor
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.Noticias
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriasViewModel : ViewModel() {

    val categories : MutableLiveData<Noticias> = MutableLiveData()
    val interactor = NoticiasInteractor()

    fun btnGetCategory(category : String, language: String){
        CoroutineScope(Dispatchers.IO).launch {
            categories.postValue(interactor.traerNoticiasCat(category, language))
        }
    }
}