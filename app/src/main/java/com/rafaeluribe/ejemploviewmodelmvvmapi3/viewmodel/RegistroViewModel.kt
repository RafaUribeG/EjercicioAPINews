package com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor.RegistroInteractor
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.Usuario
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel() {

    var usuarios : MutableLiveData<UsuarioItem> = MutableLiveData()
    private val registroInteractor = RegistroInteractor()

     fun onBtnValidarUsuarioRegistro(usuario: String){
        CoroutineScope(Dispatchers.IO).launch {

            var x: Usuario? = registroInteractor.validarUsuario(usuario)


        }
    }
}