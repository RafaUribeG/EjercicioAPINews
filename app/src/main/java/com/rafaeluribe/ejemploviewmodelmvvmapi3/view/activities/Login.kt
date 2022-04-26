package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rafaeluribe.ejemploviewmodelmvvmapi3.R
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityLoginBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.RestEngine
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.Usuario
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call


class Login : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistro.setOnClickListener {
            val intent = Intent(applicationContext, Registro::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            binding.barraProgreso.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {
                var usu = binding.txtUser.text.toString()
                var pass = binding.txtPass.text.toString()

                var aux2 = validarUsuario(usu, pass)

                if (aux2 == 0){
                    runOnUiThread {
                        Toast.makeText(applicationContext, "El usuario no existe!",
                        Toast.LENGTH_SHORT).show()
                        binding.barraProgreso.visibility = View.GONE
                    }
                }
                else{
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun validarUsuario(usu: String, pass: String): Int {
        val llamada : UsuarioAPIService =
            RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
        val resultado : Call<Usuario> = llamada.obtenerUsuario("bd.json")
        val u:Usuario? = resultado.execute().body()
        var aux: Int = 0

        for (i in u!!){
            if (i.usuario == usu && i.constrasena == pass){
                aux = 1
            }
            else{
                aux = 0
            }
        }
        return aux
    }
}