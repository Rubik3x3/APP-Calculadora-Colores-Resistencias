package com.rubik.programaresistencia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SpashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this,InicioActivity::class.java))
        finish()
    }
}