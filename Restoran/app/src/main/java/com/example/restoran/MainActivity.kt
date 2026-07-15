package com.example.restoran

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restoran.databinding.ActivityMainBinding
import com.example.restoran.model.MakananItem
import com.example.restoran.model.MinumanItem
import com.example.restoran.model.OrderItem
import com.example.restoran.util.ReceiptFormatter
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var makananAdapter: MakananAdapter
    private lateinit var minumanAdapter: MinumanAdapter
    private lateinit var orderAdapter: OrderAdapter

    private val makananList = listOf(
        MakananItem(1, "Nasi Goreng Spesial 🍳", 25000),
        MakananItem(2, "Ayam Bakar Madu 🍗", 30000),
        MakananItem(3, "Mie Goreng Jawa 🍜", 22000),
        MakananItem(4, "Sate Ayam Madura 🍢", 28000),
        MakananItem(5, "Bakso Urat 🍲", 20000),
        MakananItem(6, "Rendang Sapi 🍛", 35000),
        MakananItem(7, "Ikan Bakar 🐟", 40000),
        MakananItem(8, "Gado-Gado 🥗", 18000)
    )

    private val minumanList = listOf(
        MinumanItem(1, "Es Teh Manis 🥤", 5000),
        MinumanItem(2, "Jus Alpukat Kocok 🥑", 15000),
        MinumanItem(3, "Kopi Susu Gula Aren ☕", 12000),
        MinumanItem(4, "Air Mineral Dingin 💧", 4000),
        MinumanItem(5, "Es Jeruk Peras 🍊", 8000),
        MinumanItem(6, "Soda Gembira 🍹", 13000),
        MinumanItem(7, "Thai Tea 🧋", 15000),
        MinumanItem(8, "Es Campur 🍧", 18000)
    )

    private val orderList = mutableListOf<OrderItem>()
    private val rupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))

    private var totalBayar = 0
    private var uangBayar = 0
    private var kembalian = 0
    private var statusPembayaran = "Belum Dibayar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMakananRecycler()
        setupMinumanRecycler()
        setupOrderRecycler()
        setupActions()
        updateTotal()
    }

    private fun setupMakananRecycler() {
        makananAdapter = MakananAdapter(makananList) { makanan ->
            addMakananOrder(makanan)
        }
        binding.rvMakanan.layoutManager = LinearLayoutManager(this)
        binding.rvMakanan.adapter = makananAdapter
    }

    private fun setupMinumanRecycler() {
        minumanAdapter = MinumanAdapter(minumanList) { minuman ->
            addMinumanOrder(minuman)
        }
        binding.rvMinuman.layoutManager = LinearLayoutManager(this)
        binding.rvMinuman.adapter = minumanAdapter
    }

    private fun setupOrderRecycler() {
        orderAdapter = OrderAdapter(orderList) {
            updateTotal()
        }
        binding.rvOrder.layoutManager = LinearLayoutManager(this)
        binding.rvOrder.adapter = orderAdapter
    }

    private fun setupActions() {
        binding.btnBayar.setOnClickListener {
            prosesPembayaran()
        }

        binding.btnCetak.setOnClickListener {
            cetakStruk()
        }

        binding.btnReset.setOnClickListener {
            resetPesanan()
        }
    }

    private fun addMakananOrder(makanan: MakananItem) {
        resetStatusJikaLunas()
        val existing = orderList.find { it.name == makanan.nama && it.type == "MAKANAN" }

        if (existing != null) {
            existing.qty += 1
        } else {
            orderList.add(OrderItem(makanan.nama, makanan.harga, 1, "MAKANAN"))
        }

        orderAdapter.notifyDataSetChanged()
        updateTotal()
    }

    private fun addMinumanOrder(minuman: MinumanItem) {
        resetStatusJikaLunas()
        val existing = orderList.find { it.name == minuman.nama && it.type == "MINUMAN" }

        if (existing != null) {
            existing.qty += 1
        } else {
            orderList.add(OrderItem(minuman.nama, minuman.harga, 1, "MINUMAN"))
        }

        orderAdapter.notifyDataSetChanged()
        updateTotal()
    }

    private fun resetStatusJikaLunas() {
        if (statusPembayaran == "Lunas") {
            statusPembayaran = "Belum Dibayar"
            binding.tvKembalian.text = "Kembalian: Rp0"
            binding.etBayar.setText("")
        }
    }

    private fun updateTotal() {
        totalBayar = orderList.sumOf { it.subtotal() }
        binding.tvTotal.text = "Total: ${rupiah.format(totalBayar)}"
        binding.tvStatus.text = "Status: $statusPembayaran"
    }

    private fun prosesPembayaran() {
        if (orderList.isEmpty()) {
            Toast.makeText(this, "Pesanan masih kosong 🛒", Toast.LENGTH_SHORT).show()
            return
        }

        val bayarText = binding.etBayar.text?.toString()?.trim().orEmpty()
        if (bayarText.isEmpty()) {
            binding.etBayar.error = "Masukkan nominal bayar 💰"
            return
        }

        uangBayar = bayarText.toIntOrNull() ?: 0
        if (uangBayar < totalBayar) {
            Toast.makeText(this, "Uang bayar kurang ⚠️", Toast.LENGTH_SHORT).show()
            return
        }

        kembalian = uangBayar - totalBayar
        statusPembayaran = "Lunas"
        binding.tvKembalian.text = "Kembalian: ${rupiah.format(kembalian)}"
        binding.tvStatus.text = "Status: $statusPembayaran"

        Toast.makeText(this, "Pembayaran berhasil! ✅", Toast.LENGTH_SHORT).show()
    }

    private fun cetakStruk() {
        if (orderList.isEmpty()) {
            Toast.makeText(this, "Belum ada pesanan 🛒", Toast.LENGTH_SHORT).show()
            return
        }

        if (statusPembayaran != "Lunas") {
            Toast.makeText(this, "Selesaikan pembayaran terlebih dahulu 💸", Toast.LENGTH_SHORT).show()
            return
        }

        val receiptText = ReceiptFormatter.formatReceipt(
            orderItems = orderList,
            total = totalBayar,
            bayar = uangBayar,
            kembalian = kembalian,
            status = statusPembayaran
        )
        AlertDialog.Builder(this)
            .setTitle("Struk Pembayaran 🧾")
            .setMessage(receiptText)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun resetPesanan() {
        orderList.clear()
        orderAdapter.notifyDataSetChanged()
        binding.etBayar.setText("")
        totalBayar = 0
        uangBayar = 0
        kembalian = 0
        statusPembayaran = "Belum Dibayar"
        binding.tvKembalian.text = "Kembalian: Rp0"
        updateTotal()
        Toast.makeText(this, "Pesanan baru dimulai ✨", Toast.LENGTH_SHORT).show()
    }
}