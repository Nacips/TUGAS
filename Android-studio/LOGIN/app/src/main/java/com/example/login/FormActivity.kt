package com.example.login

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import java.util.Calendar
class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        val etNim = findViewById<EditText>(R.id.etNim)
        val etNama = findViewById<EditText>(R.id.etNama)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val etTanggal = findViewById<EditText>(R.id.etTanggal)
        val etAlamat = findViewById<EditText>(R.id.etAlamat)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        etTanggal.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, yearSelected, monthOfYear, dayOfMonth ->
                // Menampilkan tanggal yang dipilih (bulan ditambah 1 karena index mulai dari 0)
                val tgl = "$dayOfMonth/${monthOfYear + 1}/$yearSelected"
                etTanggal.setText(tgl)
            }, year, month, day)

            dpd.show()
        }

        btnSimpan.setOnClickListener {
            val nim = etNim.text.toString()
            val nama = etNama.text.toString()
            val tanggal = etTanggal.text.toString()
            val alamat = etAlamat.text.toString()
            val selectedId = rgGender.checkedRadioButtonId
            val gender = if (selectedId != -1) {
                findViewById<RadioButton>(selectedId).text.toString()
            } else {
                "Belum dipilih"
            }
            if (nim.isEmpty() || nama.isEmpty()) {
                Toast.makeText(this, "NIM dan Nama wajib diisi!",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Data Tersimpan:\n$nim - $nama\n$gender\n$tanggal\n$alamat",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
