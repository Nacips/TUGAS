// --- ProfilActivity.kt ---
package com.example.siakad_dashboard
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.siakad_dashboard.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}