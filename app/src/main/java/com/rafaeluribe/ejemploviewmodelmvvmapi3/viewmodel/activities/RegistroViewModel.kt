package com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.interactor.RegistroInteractor
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.Usuario
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistroViewModel : ViewModel() {

    var usuarios : MutableLiveData<Int> = MutableLiveData()
    private val registroInteractor = RegistroInteractor()

     fun onBtnValidarUsuarioRegistro(usuarioItem: UsuarioItem){
        CoroutineScope(Dispatchers.IO).launch {

            val x: Usuario? = registroInteractor.validarUsuario(usuarioItem.usuario)

            if(x == null){
                //var cant = registroInteractor.cantidadRegistros()
                val aux: Int =
                    withContext(Dispatchers.Default) {
                        registroInteractor.cantidadRegistros()
                    }

                registroInteractor.agregarUsuario(aux, usuarioItem)
                usuarios.postValue(1)
            }
            else{
                usuarios.postValue(0)
            }
        }
    }
}