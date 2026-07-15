package com.example.bluecinema.util

import com.example.bluecinema.model.PaymentResult
import com.example.bluecinema.model.SummaryItem

object SessionCart {

    // =========================
    // LIST KERANJANG
    // =========================
    val items = mutableListOf<SummaryItem>()

    // =========================
    // HASIL PEMBAYARAN
    // =========================
    var paymentResult: PaymentResult? = null

    // =========================
    // TAMBAH ITEM
    // =========================
    fun addItem(newItem: SummaryItem) {

        val existingItem = items.find {

            it.name == newItem.name &&
                    it.category == newItem.category
        }

        if (existingItem != null) {

            existingItem.qty += 1

        } else {

            newItem.qty = 1

            items.add(newItem)
        }
    }

    // =========================
    // TAMBAH QTY
    // =========================
    fun increaseItem(item: SummaryItem) {

        val existingItem = findItem(item)

        existingItem?.qty =
            (existingItem?.qty ?: 0) + 1
    }

    // =========================
    // KURANGI QTY
    // =========================
    fun decreaseItem(item: SummaryItem) {

        val existingItem = findItem(item)

        existingItem?.let {

            if (it.qty > 1) {

                it.qty -= 1

            } else {

                items.remove(it)
            }
        }
    }

    // =========================
    // HAPUS ITEM
    // =========================
    fun removeItem(item: SummaryItem) {

        items.remove(item)
    }

    // =========================
    // CARI ITEM
    // =========================
    private fun findItem(item: SummaryItem): SummaryItem? {

        return items.find {

            it.name == item.name &&
                    it.category == item.category
        }
    }

    // =========================
    // TOTAL HARGA
    // =========================
    fun totalPrice(): Int {

        return items.sumOf {
            it.subtotal()
        }
    }

    // =========================
    // TOTAL ITEM
    // =========================
    fun totalItem(): Int {

        return items.sumOf {
            it.qty
        }
    }

    // =========================
    // TOTAL FILM
    // =========================
    fun totalFilm(): Int {

        return items.sumOf {

            if (it.category.equals("film", true))
                it.qty
            else
                0
        }
    }

    // =========================
    // TOTAL SNACK & MINUMAN
    // =========================
    fun totalSnack(): Int {

        return items.sumOf {

            if (
                it.category.equals("snack", true) ||
                it.category.equals("minuman", true)
            )
                it.qty
            else
                0
        }
    }

    // =========================
    // CEK KOSONG
    // =========================
    fun isEmpty(): Boolean {

        return items.isEmpty()
    }

    // =========================
    // RESET PEMBAYARAN
    // =========================
    fun clearPayment() {

        paymentResult = null
    }

    // =========================
    // CLEAR SEMUA
    // =========================
    fun clearAll() {

        items.clear()

        paymentResult = null
    }
}