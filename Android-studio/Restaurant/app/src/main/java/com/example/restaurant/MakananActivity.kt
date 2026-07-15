package com.example.restaurant

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MakananActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makanan)

        val m1 = findViewById<TextView>(R.id.menu1)
        val m2 = findViewById<TextView>(R.id.menu2)
        val m3 = findViewById<TextView>(R.id.menu3)
        val m4 = findViewById<TextView>(R.id.menu4)

        m1.setOnClickListener {
            Toast.makeText(this, "Burger dipilih", Toast.LENGTH_SHORT).show()
        }

        m2.setOnClickListener {
            Toast.makeText(this, "Pizza dipilih", Toast.LENGTH_SHORT).show()
        }

        m3.setOnClickListener {
            Toast.makeText(this, "Mie Ayam dipilih", Toast.LENGTH_SHORT).show()
        }

        m4.setOnClickListener {
            Toast.makeText(this, "Ayam Goreng dipilih", Toast.LENGTH_SHORT).show()
        }
    }
}