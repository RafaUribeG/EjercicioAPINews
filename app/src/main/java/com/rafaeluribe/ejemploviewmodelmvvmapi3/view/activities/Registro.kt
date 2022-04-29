package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rafaeluribe.ejemploviewmodelmvvmapi3.R
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityRegistroBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.RestEngine
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.Usuario
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioAPIService
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.usuarios.UsuarioItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //supportActionBar!!.title = "Registro de Usuarios"

        binding.btnRegistrar.setOnClickListener {
            //binding.progressBar2.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {

                var usu = binding.txtUser2.text.toString()

                var aux2 = validarUsuario(usu)


                if (aux2 == 0) {
                    val x: Int = async {
                        cantidadRegistros()
                    }.await()

                    agregarUsuario(
                        x, UsuarioItem(
                            binding.txtUser2.text.toString(),
                            binding.txtPass2.text.toString(),
                            binding.txtNombre.text.toString()
                        )
                    )
                    val intent = Intent(applicationContext, Login::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext, "No se pudo crear el usuario!", Toast.LENGTH_SHORT).show()
                    //binding.progressBar2.visibility = View.GONE
                }
            }
        }
    }

    private fun agregarUsuario(x: Int, usuarioItem: UsuarioItem) {
        CoroutineScope(Dispatchers.IO).launch {
            val llamada : UsuarioAPIService =
                RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
            val resultado : Call<UsuarioItem> = llamada.agregarUsuario(x,usuarioItem)
            val u:UsuarioItem? = resultado.execute().body()

            if (u != null){
                runOnUiThread {
                    Toast.makeText(applicationContext, "Usuario creado exitosamente!!",
                    Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(applicationContext, "Usuario ${usuarioItem.usuario} ya existe",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cantidadRegistros(): Int {
        val llamada: UsuarioAPIService = RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
        val resultado: Call<Usuario> = llamada.obtenerUsuario("bd.json")
        val u: Usuario? = resultado.execute().body()
        return u!!.size
    }

    private fun validarUsuario(usuario: String) : Int{
        val llamada: UsuarioAPIService =
            RestEngine.getRestEngine().create(UsuarioAPIService::class.java)
        val resultado : Call<Usuario> = llamada.obtenerUsuario("bd.json")
        val u:Usuario? = resultado.execute().body()

        var aux = 0
        for (i in u!!){
            if(i.usuario == usuario){
                aux = 1
            }
            else{
                aux = 0
            }
        }
        return aux
    }
}