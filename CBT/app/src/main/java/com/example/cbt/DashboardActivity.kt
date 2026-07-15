package com.example.cbt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class DashboardActivity : AppCompatActivity() {
    private lateinit var tvPeserta: TextView
    private lateinit var btnMulai: Button
    override fun onCreate(savedInstanceState: Bundle?)
    { super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        tvPeserta = findViewById(R.id.tvPeserta)
        btnMulai = findViewById(R.id.btnMulai)

        val username = intent.getStringExtra("username") ?: "Peserta"
        tvPeserta.text = "Selamat Datang, $username"
        btnMulai.setOnClickListener {
            startActivity(Intent(this, UjianActivity::class.java))
        }
    }
}