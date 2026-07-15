package com.example.bluecinema

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bluecinema.databinding.ActivityReceiptBinding
import com.example.bluecinema.util.MoneyHelper
import com.example.bluecinema.util.SessionCart

class ReceiptActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showReceipt()
        setupAction()
    }

    // TAMPILKAN STRUK
    private fun showReceipt() {

        val result = SessionCart.paymentResult ?: return

        // STATUS
        binding.tvReceiptStatus.text =
            "${result.status} ✅"

        // BUILDER STRUK
        val builder = StringBuilder()

        builder.append("🎬 BLUECINEMA\n")
        builder.append("Premium Entertainment\n")
        builder.append("================================\n\n")

        // LIST ITEM
        SessionCart.items.forEach { item ->

            builder.append("${item.name}\n")
            builder.append(
                "${item.qty} x ${
                    MoneyHelper.toRupiah(item.price)
                }\n"
            )

            builder.append(
                "Subtotal : ${
                    MoneyHelper.toRupiah(item.subtotal())
                }\n"
            )

            builder.append("--------------------------------\n")
        }

        // TOTAL PEMBAYARAN
        builder.append("\n")
        builder.append("💳 DETAIL PEMBAYARAN\n")
        builder.append("================================\n")

        builder.append(
            "Total       : ${
                MoneyHelper.toRupiah(result.total)
            }\n"
        )

        builder.append(
            "Bayar       : ${
                MoneyHelper.toRupiah(result.paid)
            }\n"
        )

        builder.append(
            "Kembalian   : ${
                MoneyHelper.toRupiah(result.change)
            }\n"
        )

        builder.append("\n================================\n")
        builder.append("✅ PEMBAYARAN BERHASIL\n")
        builder.append("Terima kasih telah menggunakan\n")
        builder.append("BlueCinema 🎟️\n\n")
        builder.append("Selamat Menonton 🍿")

        binding.tvReceiptContent.text =
            builder.toString()
    }

    // ACTION BUTTON
    private fun setupAction() {

        // CETAK STRUK
        binding.btnPrintNow.setOnClickListener {

            Toast.makeText(
                this,
                "Struk berhasil dicetak 🖨️",
                Toast.LENGTH_SHORT
            ).show()
        }

        // KEMBALI KE HOME
        binding.btnBackHome.setOnClickListener {

            Toast.makeText(
                this,
                "Terima kasih telah memesan 🎬",
                Toast.LENGTH_SHORT
            ).show()

            // HAPUS SESSION
            SessionCart.clearAll()

            // PINDAH HOME
            val intent = Intent(
                this,
                HomeActivity::class.java
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)
            finish()
        }
    }
}