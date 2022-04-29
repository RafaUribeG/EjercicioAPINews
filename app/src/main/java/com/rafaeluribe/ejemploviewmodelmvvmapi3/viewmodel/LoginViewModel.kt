package com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor.LoginInteractor
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var usuarios : MutableLiveData<Usuario> = MutableLiveData()
    private val usuarioInteractor = LoginInteractor()

    fun onBtnValidarUsuario(usuario: String, pass: String){
        CoroutineScope(Dispatchers.IO).launch {
            usuarios.postValue( usuarioInteractor.validarUsuario(usuario, pass))
        }
    }
}