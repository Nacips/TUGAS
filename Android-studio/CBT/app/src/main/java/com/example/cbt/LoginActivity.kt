package com.example.cbt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val username =
                etUsername.text.toString().trim()

            val password =
                etPassword.text.toString().trim()

            // Validasi kosong
            if (username.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "NIM dan Token wajib diisi",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // NIM dan token yang diperbolehkan
                val nimBenar = "14523206"
                val tokenBenar = "CBT2026"

                if (username == nimBenar &&
                    password == tokenBenar
                ) {

                    val intent =
                        Intent(
                            this,
                            DashboardActivity::class.java
                        )

                    intent.putExtra(
                        "username",
                        username
                    )

                    startActivity(intent)
                    finish()

                } else {

                    Toast.makeText(
                        this,
                        "NIM atau Token salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}