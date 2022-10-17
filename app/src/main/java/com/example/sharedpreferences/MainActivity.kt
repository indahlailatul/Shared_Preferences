package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.content.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val sp = getSharedPreferences("login", Context.MODE_PRIVATE)
        if (sp.contains("username") && sp.getString("username", "") != null) {
            val i = Intent(this@MainActivity, LoginActivity::class.java)
            i.putExtra("username", sp.getString("username", ""))
            startActivity(i)
            finish()
        }

        btnLogin.setOnClickListener {
            val username = "${etUsername.text}"
            val password = "${etPassword.text}"
            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (password == "123456") { //Ganti dengan NIM Kalian
                    val editor = sp.edit()
                    editor.putString("username", "${etUsername.text}")
                    editor.apply()

                    val i = Intent(this@MainActivity, LoginActivity::class.java)
                    i.putExtra("username", sp.getString("username", ""))
                    startActivity(i)
                    finish()

                    Toast.makeText(this@MainActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                } else {
                    etUsername.setText("")
                    etPassword.setText("")
                    etUsername.requestFocus()
                    Toast.makeText(this@MainActivity, "Password salah!", Toast.LENGTH_SHORT).show()
                }

            } else {
                etUsername.setText("")
                etPassword.setText("")
                etUsername.requestFocus()
                Toast.makeText(
                    this@MainActivity,
                    "Username dan password tidak boleh kosong!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}