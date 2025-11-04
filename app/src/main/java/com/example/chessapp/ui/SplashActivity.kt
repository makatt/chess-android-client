package com.example.chessapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.chessapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Имитация загрузки 2 секунды
        Handler(Looper.getMainLooper()).postDelayed({
            // Проверка, авторизован ли пользователь (пока просто переход)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}
