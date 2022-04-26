package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}