package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityVistaDetalleNoticiasBinding

class VistaDetalleNoticias : AppCompatActivity() {

    //ViewBinding
    private lateinit var b: ActivityVistaDetalleNoticiasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityVistaDetalleNoticiasBinding.inflate(layoutInflater)
        setContentView(b.root)
    }
}