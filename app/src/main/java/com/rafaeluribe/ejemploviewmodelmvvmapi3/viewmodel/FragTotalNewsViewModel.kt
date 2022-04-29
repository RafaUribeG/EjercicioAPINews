package com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor.FragTotalNewsInteractor
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor.NoticiasInteractor
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.Noticias
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragTotalNewsViewModel : ViewModel() {

    val noticias: MutableLiveData<Noticias> = MutableLiveData()
    val interactor = FragTotalNewsInteractor()

    fun onBtnTraerNoticias(){
        CoroutineScope(Dispatchers.IO).launch {
            noticias.postValue(interactor.traerRespuesta("es"))
        }
    }

}