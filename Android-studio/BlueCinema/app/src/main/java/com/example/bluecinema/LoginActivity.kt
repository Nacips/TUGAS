package com.example.bluecinema

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bluecinema.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // AKUN DUMMY
    private val validEmail = "shipaamalia@gmail.com"
    private val validPassword = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {

        // BUTTON LOGIN
        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            when {

                email.isEmpty() -> {
                    binding.etEmail.error = "Email wajib diisi"
                    binding.etEmail.requestFocus()
                }

                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.etEmail.error = "Format email tidak valid"
                    binding.etEmail.requestFocus()
                }

                password.isEmpty() -> {
                    binding.etPassword.error = "Password wajib diisi"
                    binding.etPassword.requestFocus()
                }

                password.length < 6 -> {
                    binding.etPassword.error = "Password minimal 6 karakter"
                    binding.etPassword.requestFocus()
                }

                // CEK EMAIL & PASSWORD
                email != validEmail || password != validPassword -> {

                    Toast.makeText(
                        this,
                        "Email atau password salah ❌",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {

                    Toast.makeText(
                        this,
                        "Login berhasil 🎉",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(this, HomeActivity::class.java)
                    )

                    finish()
                }
            }
        }

        // LOGIN SEBAGAI TAMU
        binding.tvGuest.setOnClickListener {

            Toast.makeText(
                this,
                "Masuk sebagai tamu 👤",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(
                Intent(this, HomeActivity::class.java)
            )

            finish()
        }
    }
}