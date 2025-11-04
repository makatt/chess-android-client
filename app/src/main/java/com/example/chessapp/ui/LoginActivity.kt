package com.example.chessapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.chessapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnLogin.setOnClickListener {

            startActivity(Intent(this, MainMenuActivity::class.java))
        }

        btnRegister.setOnClickListener {
            // пока просто переходим в меню
            startActivity(Intent(this, MainMenuActivity::class.java))
        }
    }
}
