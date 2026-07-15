// --- NilaiActivity.kt ---
package com.example.siakad_dashboard
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.siakad_dashboard.databinding.ActivityNilaiBinding

class NilaiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNilaiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNilaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}