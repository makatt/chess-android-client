package com.example.chessapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.chessapp.R
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val avatar = findViewById<ImageView>(R.id.profileAvatar)
        val name = findViewById<TextView>(R.id.profileName)
        val stats = findViewById<TextView>(R.id.profileStats)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        // Пример данных
        name.text = "Maxim"
        stats.text = "Рейтинг: 1450\nПобед: 24 / Поражений: 10"

        // Пример загрузки аватара (позже можно заменить на URL)
        Picasso.get()
            .load(R.drawable.ic_chess_logo)
            .into(avatar)

        // Переход обратно в меню
        btnBack.setOnClickListener {
            finish()
        }

    }
}
