package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rafaeluribe.ejemploviewmodelmvvmapi3.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, Login::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}