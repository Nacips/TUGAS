package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ==========================
        // TAMPILKAN USERNAME
        // ==========================
        val username = intent.getStringExtra("username") ?: "User"
        binding.txtUsername.text = "Selamat Datang, $username"

        // ==========================
        // MENU MAKANAN
        // ==========================
        binding.menuMakanan.setOnClickListener {
            Toast.makeText(this, "Menu Makanan", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MakananActivity::class.java))
        }

        // ==========================
        // MENU MINUMAN
        // ==========================
        binding.menuMinuman.setOnClickListener {
            Toast.makeText(this, "Menu Minuman", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MinumanActivity::class.java))
        }

        // ==========================
        // MENU PEMESANAN
        // ==========================
        binding.menuPemesanan.setOnClickListener {
            Toast.makeText(this, "Pemesanan", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, PemesananActivity::class.java))
        }

        // ==========================
        // MENU ABOUT
        // ==========================
        binding.menuAbout.setOnClickListener {
            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
}