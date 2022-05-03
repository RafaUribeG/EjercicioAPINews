package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityRegistroBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioItem
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.activities.RegistroViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Registro : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityRegistroBinding

    //ViewModel
    private lateinit var registroViewModel: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viemodel
        registroViewModel = ViewModelProvider(this).get(RegistroViewModel::class.java)
        observar()

        binding.btnRegistrar.setOnClickListener {
            //binding.progressBar2.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                var user = UsuarioItem(binding.txtNombre.text.toString(),
                                       binding.txtUser2.text.toString(),
                                       binding.txtPass2.text.toString())

                registroViewModel.onBtnValidarUsuarioRegistro(user)
            }
        }
    }

    private fun observar() {
        registroViewModel.usuarios.observe(this, Observer {
            if (registroViewModel.usuarios != null){
                val intent = Intent(applicationContext, Login::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Usuario registrado correctamente!",
                              Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext, "Usuario ya registrado", Toast.LENGTH_SHORT).show()
            }
        })
    }
}