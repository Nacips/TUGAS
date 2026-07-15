package com.example.stikomcinema

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stikomcinema.databinding.ActivityMainBinding
import com.example.stikomcinema.model.FilmItem
import com.example.stikomcinema.model.FoodItem
import com.example.stikomcinema.model.OrderItem
import com.example.stikomcinema.util.ReceiptFormatter
import java.text.NumberFormat
import java.util.Locale
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var filmAdapter: FilmAdapter
    private lateinit var snackAdapter: SnackAdapter
    private lateinit var orderAdapter: OrderAdapter

    private val filmList = listOf(
        FilmItem(1, "STIKOM Robot AI", "Studio 1", "13:00", 45000),
        FilmItem(2, "Pencuri Skripsi", "Studio 2", "15:30", 50000),
        FilmItem(3, "STIK komen", "Studio 3", "19:00", 55000)
    )

    private val foodList = listOf(
        FoodItem(1, "Popcorn Caramel", "Makanan", 25000),
        FoodItem(2, "Nachos", "Makanan", 30000),
        FoodItem(3, "Coca Cola", "Minuman", 15000),
        FoodItem(4, "Air Mineral", "Minuman", 10000)
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

        setupFilmRecycler()
        setupFoodRecycler()
        setupOrderRecycler()
        setupActions()
        updateTotal()
    }

    private fun setupFilmRecycler() {
        filmAdapter = FilmAdapter(filmList) { film ->
            addFilmOrder(film)
        }

        binding.rvFilm.layoutManager = LinearLayoutManager(this)
        binding.rvFilm.adapter = filmAdapter
    }

    private fun setupFoodRecycler() {
        snackAdapter = SnackAdapter(foodList) { food ->
            addFoodOrder(food)
        }

        binding.rvFood.layoutManager = LinearLayoutManager(this)
        binding.rvFood.adapter = snackAdapter
    }

    private fun setupOrderRecycler() {
        orderAdapter = OrderAdapter(orderList)
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

    private fun addFilmOrder(film: FilmItem) {
        val name = "${film.title} - ${film.schedule}"
        val existing = orderList.find { it.name == name && it.type == "FILM" }

        if (existing != null) {
            existing.qty += 1
        } else {
            orderList.add(OrderItem(name, film.price, 1, "FILM"))
        }

        orderAdapter.notifyDataSetChanged()
        updateTotal()
    }

    private fun addFoodOrder(food: FoodItem) {
        val existing = orderList.find { it.name == food.name && it.type == "FOOD" }

        if (existing != null) {
            existing.qty += 1
        } else {
            orderList.add(OrderItem(food.name, food.price, 1, "FOOD"))
        }

        orderAdapter.notifyDataSetChanged()
        updateTotal()
    }

    private fun updateTotal() {
        totalBayar = orderList.sumOf { it.subtotal() }
        binding.tvTotal.text = "Total: ${rupiah.format(totalBayar)}"
        binding.tvStatus.text = "Status: $statusPembayaran"
    }

    private fun prosesPembayaran() {
        if (orderList.isEmpty()) {
            Toast.makeText(this, "Pesanan masih kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val bayarText = binding.etBayar.text?.toString()?.trim().orEmpty()
        if (bayarText.isEmpty()) {
            binding.etBayar.error = "Masukkan nominal bayar"
            return
        }

        uangBayar = bayarText.toIntOrNull() ?: 0
        if (uangBayar < totalBayar) {
            Toast.makeText(this, "Uang bayar kurang", Toast.LENGTH_SHORT).show()
            return
        }

        kembalian = uangBayar - totalBayar
        statusPembayaran = "Lunas"
        binding.tvKembalian.text = "Kembalian: ${rupiah.format(kembalian)}"
        binding.tvStatus.text = "Status: $statusPembayaran"

        Toast.makeText(this, "Pembayaran berhasil", Toast.LENGTH_SHORT).show()
    }

    private fun cetakStruk() {
        if (orderList.isEmpty()) {
            Toast.makeText(this, "Belum ada pesanan", Toast.LENGTH_SHORT).show()
            return
        }

        if (statusPembayaran != "Lunas") {
            Toast.makeText(this, "Selesaikan pembayaran terlebih dahulu", Toast.LENGTH_SHORT).show()
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
            .setTitle("Struk Pembayaran")
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
        Toast.makeText(this, "Pesanan baru dimulai", Toast.LENGTH_SHORT).show()
    }
}
