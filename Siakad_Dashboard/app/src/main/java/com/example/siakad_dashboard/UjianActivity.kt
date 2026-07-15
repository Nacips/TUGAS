// --- UjianActivity.kt ---
package com.example.siakad_dashboard
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.siakad_dashboard.databinding.ActivityUjianBinding

class UjianActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUjianBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUjianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sembunyikan ActionBar bawaan
        supportActionBar?.hide()
    }
}