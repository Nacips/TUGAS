package com.example.restaurant

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PemesananActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemesanan)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etJumlah = findViewById<EditText>(R.id.etJumlah)
        val spinner = findViewById<Spinner>(R.id.spinnerMenu)
        val btnPesan = findViewById<Button>(R.id.btnPesan)
        val txtHasil = findViewById<TextView>(R.id.txtHasil)

        // Data menu
        val menu = arrayOf(
            "Burger - 20000",
            "Pizza - 35000",
            "Mie Ayam - 15000",
            "Ayam Goreng - 18000",
            "Es Teh - 5000",
            "Kopi - 10000"
        )

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, menu)
        spinner.adapter = adapter

        btnPesan.setOnClickListener {

            val nama = etNama.text.toString()
            val jumlah = etJumlah.text.toString()

            if (nama.isEmpty() || jumlah.isEmpty()) {
                Toast.makeText(this, "Isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selected = spinner.selectedItem.toString()
            val harga = selected.split(" - ")[1].toInt()
            val total = harga * jumlah.toInt()

            val hasil = """
                Nama: $nama
                Menu: $selected
                Jumlah: $jumlah
                Total: Rp $total
            """.trimIndent()

            txtHasil.text = hasil
        }
    }
}