package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.rafaeluribe.ejemploviewmodelmvvmapi3.R
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityLoginBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.RestEngine
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.Usuario
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioAPIService
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.FragTotalNewsViewModel
import com.rafaeluribe.ejemploviewmodelmvvmapi3.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call


class Login : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding : ActivityLoginBinding

    //ViewModel
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ViewModel
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        observar()

        binding.btnRegistro.setOnClickListener {
            val intent = Intent(applicationContext, Registro::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            binding.barraProgreso.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {
                var usu = binding.txtUser.text.toString()
                var pass = binding.txtPass.text.toString()

                loginViewModel.onBtnValidarUsuario(usu, pass)
            }
        }
    }

    private fun observar() {
        loginViewModel.usuarios.observe(this, Observer {
            binding.barraProgreso.visibility = View.GONE
            if (loginViewModel.usuarios != null){
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(applicationContext, "Usuario no registrado", Toast.LENGTH_SHORT).show()
            }
        })
    }
}