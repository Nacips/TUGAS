// --- PembayaranActivity.kt ---
package com.example.siakad_dashboard
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.siakad_dashboard.databinding.ActivityPembayaranBinding

class PembayaranActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}