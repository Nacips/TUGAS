package com.example.restoran

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Membuat layar menjadi full screen (opsional)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(R.layout.activity_splash)

        // Efek animasi sederhana (Fade In) pada logo
        val logo = findViewById<ImageView>(R.id.imgLogoSplash)
        logo.alpha = 0f
        logo.animate().setDuration(1500).alpha(1f)

        // Beri jeda 3 detik (3000ms) sebelum pindah ke MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Animasi perpindahan antar layar (Halus)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish() // Menutup SplashActivity agar tidak bisa di-back
        }, 3000)
    }
}