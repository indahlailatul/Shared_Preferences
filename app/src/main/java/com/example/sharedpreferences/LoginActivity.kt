package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.content.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val username = intent.getStringExtra("username")
        tvWelcome.text = "Selamat datang, $username!"

        btnLogout.setOnClickListener {
            val sp = getSharedPreferences("login", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.remove("username")
            editor.apply()
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }
}