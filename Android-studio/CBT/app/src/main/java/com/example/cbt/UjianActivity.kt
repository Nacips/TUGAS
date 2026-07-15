package com.example.cbt

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.cbt.model.Soal

class UjianActivity : AppCompatActivity() {

    private lateinit var tvTimer: TextView
    private lateinit var tvNomorSoal: TextView
    private lateinit var tvPertanyaan: TextView
    private lateinit var rgPilihan: RadioGroup

    private lateinit var rbA: RadioButton
    private lateinit var rbB: RadioButton
    private lateinit var rbC: RadioButton
    private lateinit var rbD: RadioButton

    private lateinit var btnSebelumnya: Button
    private lateinit var btnSelanjutnya: Button
    private lateinit var btnSelesai: Button

    private var nomorSoal = 0
    private var pelanggaran = 0

    private val jawabanPeserta = HashMap<Int, String>()

    private val daftarSoal = arrayListOf(

        Soal(
            "Apa fungsi utama sistem operasi?",
            "Mengatur perangkat keras dan perangkat lunak",
            "Membuat desain grafis",
            "Menghapus seluruh data",
            "Mempercepat internet",
            "A"
        ),

        Soal(
            "RAM termasuk jenis memori apa?",
            "Memori sekunder",
            "Memori utama",
            "Memori eksternal",
            "Memori cadangan",
            "B"
        ),

        Soal(
            "CPU sering disebut sebagai?",
            "Otak komputer",
            "Penyimpanan data",
            "Layar komputer",
            "Perangkat input",
            "A"
        ),

        Soal(
            "Contoh perangkat input adalah?",
            "Monitor",
            "Printer",
            "Keyboard",
            "Speaker",
            "C"
        ),

        Soal(
            "Ekstensi file Android Package adalah?",
            ".exe",
            ".docx",
            ".apk",
            ".pdf",
            "C"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mencegah screenshot dan rekam layar sederhana
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        setContentView(R.layout.activity_ujian)

        initView()
        aktifkanModeAman()
        mulaiTimer()
        tampilkanSoal()

        btnSebelumnya.setOnClickListener {
            simpanJawaban()

            if (nomorSoal > 0) {
                nomorSoal--
                tampilkanSoal()
            }
        }

        btnSelanjutnya.setOnClickListener {
            simpanJawaban()

            if (nomorSoal < daftarSoal.size - 1) {
                nomorSoal++
                tampilkanSoal()
            }
        }

        btnSelesai.setOnClickListener {
            simpanJawaban()
            konfirmasiSelesai()
        }
    }

    private fun initView() {

        tvTimer = findViewById(R.id.tvTimer)
        tvNomorSoal = findViewById(R.id.tvNomorSoal)
        tvPertanyaan = findViewById(R.id.tvPertanyaan)
        rgPilihan = findViewById(R.id.rgPilihan)

        rbA = findViewById(R.id.rbA)
        rbB = findViewById(R.id.rbB)
        rbC = findViewById(R.id.rbC)
        rbD = findViewById(R.id.rbD)

        btnSebelumnya = findViewById(R.id.btnSebelumnya)
        btnSelanjutnya = findViewById(R.id.btnSelanjutnya)
        btnSelesai = findViewById(R.id.btnSelesai)
    }

    private fun aktifkanModeAman() {

        // Membatasi tombol back
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {

                    Toast.makeText(
                        this@UjianActivity,
                        "Tombol kembali dinonaktifkan selama ujian",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )

        // Fullscreen sederhana
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        // Lock Task Mode
        // Akan bekerja maksimal jika aplikasi dijadikan Device Owner / Kiosk App
        try {
            startLockTask()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun tampilkanSoal() {

        val soal = daftarSoal[nomorSoal]

        tvNomorSoal.text = "Soal ${nomorSoal + 1} dari ${daftarSoal.size}"
        tvPertanyaan.text = soal.pertanyaan

        rbA.text = "A. ${soal.pilihanA}"
        rbB.text = "B. ${soal.pilihanB}"
        rbC.text = "C. ${soal.pilihanC}"
        rbD.text = "D. ${soal.pilihanD}"

        rgPilihan.clearCheck()

        when (jawabanPeserta[nomorSoal]) {
            "A" -> rbA.isChecked = true
            "B" -> rbB.isChecked = true
            "C" -> rbC.isChecked = true
            "D" -> rbD.isChecked = true
        }
    }

    private fun simpanJawaban() {

        val selectedId = rgPilihan.checkedRadioButtonId

        when (selectedId) {
            R.id.rbA -> jawabanPeserta[nomorSoal] = "A"
            R.id.rbB -> jawabanPeserta[nomorSoal] = "B"
            R.id.rbC -> jawabanPeserta[nomorSoal] = "C"
            R.id.rbD -> jawabanPeserta[nomorSoal] = "D"
        }
    }

    private fun mulaiTimer() {

        val waktuUjian = 10 * 60 * 1000L

        object : CountDownTimer(waktuUjian, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                val menit = millisUntilFinished / 1000 / 60
                val detik = millisUntilFinished / 1000 % 60

                tvTimer.text =
                    String.format("Sisa Waktu: %02d:%02d", menit, detik)
            }

            override fun onFinish() {
                hitungNilai()
            }

        }.start()
    }

    private fun konfirmasiSelesai() {

        AlertDialog.Builder(this)
            .setTitle("Selesai Ujian")
            .setMessage("Apakah Anda yakin ingin menyelesaikan ujian?")
            .setPositiveButton("Ya") { _, _ ->
                hitungNilai()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun hitungNilai() {

        var benar = 0

        for (i in daftarSoal.indices) {

            if (jawabanPeserta[i] == daftarSoal[i].jawabanBenar) {
                benar++
            }
        }

        val nilai = benar * 100 / daftarSoal.size

        try {
            stopLockTask()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        AlertDialog.Builder(this)
            .setTitle("Hasil Ujian")
            .setMessage(
                "Jawaban Benar: $benar dari ${daftarSoal.size}\n" +
                        "Nilai: $nilai\n" +
                        "Pelanggaran keluar aplikasi: $pelanggaran"
            )
            .setCancelable(false)
            .setPositiveButton("Selesai") { _, _ ->
                finish()
            }
            .show()
    }

    override fun onPause() {
        super.onPause()

        // Deteksi jika peserta keluar dari aplikasi
        pelanggaran++

        if (pelanggaran >= 2) {

            Toast.makeText(
                this,
                "Terdeteksi keluar dari aplikasi CBT",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (!hasFocus) {

            pelanggaran++

        } else {

            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }
}