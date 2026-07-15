package com.example.bluecinema

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluecinema.adapter.FilmAdapter
import com.example.bluecinema.adapter.OnGoingFilmAdapter
import com.example.bluecinema.adapter.SnackAdapter
import com.example.bluecinema.adapter.SummaryAdapter
import com.example.bluecinema.databinding.ActivityHomeBinding
import com.example.bluecinema.model.FilmItem
import com.example.bluecinema.model.SnackItem
import com.example.bluecinema.model.SummaryItem
import com.example.bluecinema.util.DataDummy
import com.example.bluecinema.util.MoneyHelper
import com.example.bluecinema.util.SessionCart

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var onGoingFilmAdapter: OnGoingFilmAdapter
    private lateinit var filmAdapter: FilmAdapter
    private lateinit var snackAdapter: SnackAdapter
    private lateinit var summaryAdapter: SummaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupAction()
        updateSummary()
    }

    override fun onResume() {
        super.onResume()
        summaryAdapter.notifyDataSetChanged()
        updateSummary()
    }

    // =========================
    // SETUP RECYCLER VIEW
    // =========================
    private fun setupRecycler() {

        val allFilms = DataDummy.getAllFilms()

        // 🎬 ONGOING FILM
        val ongoingFilms = allFilms.filter { it.isOngoing }

        onGoingFilmAdapter = OnGoingFilmAdapter(ongoingFilms) { film ->
            addFilmToCart(film)
        }

        binding.rvOnGoingFilm.apply {
            layoutManager = LinearLayoutManager(
                this@HomeActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = onGoingFilmAdapter
        }

        // 🎞️ MENU FILM (NON-ONGOING)
        val menuFilms = allFilms

        filmAdapter = FilmAdapter(menuFilms) { film ->
            addFilmToCart(film)
        }

        binding.rvFilm.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            adapter = filmAdapter
        }

        // 🍿 SNACK
        snackAdapter = SnackAdapter(DataDummy.getSnacks()) { snack ->
            addSnackToCart(snack)
        }

        binding.rvSnackDrink.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            adapter = snackAdapter
        }

        // 🧾 SUMMARY
        summaryAdapter = SummaryAdapter(SessionCart.items) {
            summaryAdapter.notifyDataSetChanged()
            updateSummary()
        }

        binding.rvSummary.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = summaryAdapter
        }
    }

    // =========================
    // BUTTON ACTION
    // =========================
    private fun setupAction() {

        binding.btnToPayment.setOnClickListener {

            if (SessionCart.isEmpty()) {

                Toast.makeText(
                    this,
                    "⚠️ Pilih film atau snack terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                Toast.makeText(
                    this,
                    "🎟️ Menuju pembayaran...",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(this, PaymentActivity::class.java)
                )
            }
        }
    }

    // =========================
    // TAMBAH FILM
    // =========================
    private fun addFilmToCart(film: FilmItem) {

        SessionCart.addItem(
            SummaryItem(
                id = film.id,
                name = "${film.title} (${film.studio})",
                category = "Film",
                price = film.price,
                imageRes = film.imageRes
            )
        )

        summaryAdapter.notifyDataSetChanged()
        updateSummary()

        Toast.makeText(
            this,
            "🎬 ${film.title} ditambahkan ke keranjang",
            Toast.LENGTH_SHORT
        ).show()
    }

    // =========================
    // TAMBAH SNACK
    // =========================
    private fun addSnackToCart(snack: SnackItem) {

        SessionCart.addItem(
            SummaryItem(
                id = snack.id,
                name = snack.name,
                category = snack.category,
                price = snack.price,
                imageRes = snack.imageRes
            )
        )

        summaryAdapter.notifyDataSetChanged()
        updateSummary()

        Toast.makeText(
            this,
            "🍿 ${snack.name} ditambahkan ke keranjang",
            Toast.LENGTH_SHORT
        ).show()
    }

    // =========================
    // UPDATE SUMMARY
    // =========================
    private fun updateSummary() {

        val totalPrice = SessionCart.totalPrice()
        val totalItem = SessionCart.totalItem()

        binding.tvTotal.text = MoneyHelper.toRupiah(totalPrice)
        binding.tvItemCount.text = "$totalItem item dipilih"

        if (SessionCart.isEmpty()) {

            binding.rvSummary.visibility = View.GONE
            binding.tvEmpty.visibility = View.VISIBLE
            binding.btnToPayment.alpha = 0.5f

        } else {

            binding.rvSummary.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.GONE
            binding.btnToPayment.alpha = 1f
        }
    }
}