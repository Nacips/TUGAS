package com.example.restaurant

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MinumanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minuman)

        // Ambil TextView dari XML
        val minum1 = findViewById<TextView>(R.id.minum1)
        val minum2 = findViewById<TextView>(R.id.minum2)
        val minum3 = findViewById<TextView>(R.id.minum3)
        val minum4 = findViewById<TextView>(R.id.minum4)

        // Event klik
        minum1.setOnClickListener {
            Toast.makeText(this, "Es Teh dipilih", Toast.LENGTH_SHORT).show()
        }

        minum2.setOnClickListener {
            Toast.makeText(this, "Kopi dipilih", Toast.LENGTH_SHORT).show()
        }

        minum3.setOnClickListener {
            Toast.makeText(this, "Susu dipilih", Toast.LENGTH_SHORT).show()
        }

        minum4.setOnClickListener {
            Toast.makeText(this, "Jus Jeruk dipilih", Toast.LENGTH_SHORT).show()
        }
    }
}