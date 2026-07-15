package com.example.bluecinema.util

import java.text.NumberFormat
import java.util.Locale

object MoneyHelper {

    // FORMAT RUPIAH INDONESIA
    private val rupiahFormat =
        NumberFormat.getCurrencyInstance(
            Locale("in", "ID")
        ).apply {

            // HILANGKAN DESIMAL ",00"
            maximumFractionDigits = 0
        }

    // FORMAT KE RUPIAH
    fun toRupiah(value: Int): String {

        return rupiahFormat.format(value)
            .replace("Rp", "Rp ")
    }

    // FORMAT TANPA "RP"
    fun toNumber(value: Int): String {

        return NumberFormat.getNumberInstance(
            Locale("in", "ID")
        ).format(value)
    }

    // FORMAT SHORT
    fun shortRupiah(value: Int): String {

        return when {

            value >= 1_000_000 -> {
                "Rp ${(value / 1_000_000.0)
                    .toString()
                    .take(4)} Jt"
            }

            value >= 1_000 -> {
                "Rp ${(value / 1_000.0)
                    .toString()
                    .take(4)} Rb"
            }

            else -> {
                toRupiah(value)
            }
        }
    }
}