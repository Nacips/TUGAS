package com.example.siakad_dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.siakad_dashboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menuProfil.setOnClickListener {

            Toast.makeText(this, "Menu Profil", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)

        }

        binding.menuJadwal.setOnClickListener {

            Toast.makeText(this, "Menu Jadwal", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, JadwalActivity::class.java)
            startActivity(intent)

        }

        binding.menuNilai.setOnClickListener {

            Toast.makeText(this, "Menu Nilai",
                Toast.LENGTH_SHORT).show()

            val intent = Intent(this, NilaiActivity::class.java)
            startActivity(intent)

        }

        binding.menuUjian.setOnClickListener {

            Toast.makeText(this, "Menu Ujian",
                Toast.LENGTH_SHORT).show()

            val intent = Intent(this, UjianActivity::class.java)
            startActivity(intent)

        }

        binding.menuPembayaran.setOnClickListener {

            Toast.makeText(this, "Menu Pembayaran",
                Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PembayaranActivity::class.java)
            startActivity(intent)

        }

        binding.menuLogout.setOnClickListener {

            Toast.makeText(this, "Logout berhasil",
                Toast.LENGTH_SHORT).show()

            finish()

        }

    }
}
