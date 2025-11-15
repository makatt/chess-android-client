package com.example.chessapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.chessapp.R

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        findViewById<Button>(R.id.btnPlayOnline).setOnClickListener {
            startActivity(Intent(this, LobbyActivity::class.java))
        }
        findViewById<Button>(R.id.btnPlayLocal).setOnClickListener {
            startActivity(Intent(this, PlayLocalActivity::class.java))
        }

        findViewById<Button>(R.id.btnProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

    }
}
