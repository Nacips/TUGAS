package com.example.doa_harian

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun openMakan(view: View) {
        startActivity(Intent(this, MakanActivity::class.java))
    }
    fun openOrangTua(view: View) {
        startActivity(Intent(this, OrangTuaActivity::class.java))
    }
    fun openSelamat(view: View) {
        startActivity(Intent(this, SelamatActivity::class.java))
    }
    fun openTidur(view: View) {
        startActivity(Intent(this, TidurActivity::class.java))
    }
}