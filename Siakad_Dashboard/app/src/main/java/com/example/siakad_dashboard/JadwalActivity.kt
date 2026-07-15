// --- JadwalActivity.kt ---
package com.example.siakad_dashboard
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.siakad_dashboard.databinding.ActivityJadwalBinding

class JadwalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJadwalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}