package com.example.bluecinema

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluecinema.adapter.SummaryAdapter
import com.example.bluecinema.databinding.ActivityPaymentBinding
import com.example.bluecinema.model.PaymentResult
import com.example.bluecinema.util.MoneyHelper
import com.example.bluecinema.util.SessionCart

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding
    private lateinit var summaryAdapter: SummaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupView()
        setupAction()
    }

    // =========================
    // RECYCLER VIEW
    // =========================
    private fun setupRecycler() {

        summaryAdapter = SummaryAdapter(
            SessionCart.items
        ) {

            updatePaymentView()
        }

        binding.rvPaymentSummary.apply {

            layoutManager =
                LinearLayoutManager(this@PaymentActivity)

            adapter = summaryAdapter
        }
    }

    // =========================
    // TAMPILKAN DATA
    // =========================
    private fun setupView() {

        updatePaymentView()

        binding.btnToPrint.isEnabled = false

        binding.btnToPrint.alpha = 0.5f
    }

    // =========================
    // UPDATE UI
    // =========================
    private fun updatePaymentView() {

        val total = SessionCart.totalPrice()

        binding.tvPaymentTotal.text =
            MoneyHelper.toRupiah(total)

        binding.tvStatus.text =
            "Status: Belum Dibayar ⏳"

        binding.tvKembalian.text =
            "Kembalian: Rp0"
    }

    // =========================
    // ACTION BUTTON
    // =========================
    private fun setupAction() {

        // BUTTON BAYAR
        binding.btnBayar.setOnClickListener {

            processPayment()
        }

        // BUTTON CETAK
        binding.btnToPrint.setOnClickListener {

            if (SessionCart.paymentResult?.status == "Lunas") {

                Toast.makeText(
                    this,
                    "Membuka struk pembayaran 🧾",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(
                        this,
                        ReceiptActivity::class.java
                    )
                )

            } else {

                Toast.makeText(
                    this,
                    "Selesaikan pembayaran terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // =========================
    // PROSES PEMBAYARAN
    // =========================
    private fun processPayment() {

        // VALIDASI KERANJANG
        if (SessionCart.isEmpty()) {

            Toast.makeText(
                this,
                "Pesanan masih kosong",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val total =
            SessionCart.totalPrice()

        val bayarText =
            binding.etBayar.text.toString().trim()

        // VALIDASI INPUT
        if (bayarText.isEmpty()) {

            binding.etBayar.error =
                "Masukkan nominal pembayaran"

            binding.etBayar.requestFocus()

            return
        }

        val bayar =
            bayarText.toIntOrNull() ?: 0

        // VALIDASI ANGKA
        if (bayar <= 0) {

            binding.etBayar.error =
                "Nominal tidak valid"

            return
        }

        // UANG KURANG
        if (bayar < total) {

            binding.tvStatus.text =
                "Status: Pembayaran Gagal ❌"

            Toast.makeText(
                this,
                "Uang pembayaran kurang",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // HITUNG KEMBALIAN
        val kembalian =
            bayar - total

        // SIMPAN RESULT
        val paymentResult = PaymentResult(
            total = total,
            paid = bayar,
            change = kembalian,
            status = "Lunas"
        )

        SessionCart.paymentResult =
            paymentResult

        // UPDATE UI
        binding.tvStatus.text =
            "Status: Lunas ✅"

        binding.tvKembalian.text =
            "Kembalian: ${
                MoneyHelper.toRupiah(kembalian)
            }"

        // DISABLE BUTTON BAYAR
        binding.btnBayar.isEnabled = false

        binding.btnBayar.alpha = 0.5f

        // ENABLE BUTTON PRINT
        binding.btnToPrint.isEnabled = true

        binding.btnToPrint.alpha = 1f

        // CLEAR INPUT
        binding.etBayar.setText("")

        // SUCCESS
        Toast.makeText(
            this,
            "Pembayaran berhasil 🎉",
            Toast.LENGTH_SHORT
        ).show()
    }
}