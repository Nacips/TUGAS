package com.example.stikomcinema.util

import com.example.stikomcinema.model.OrderItem
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
object ReceiptFormatter {
    fun formatReceipt(
        orderItems: List<OrderItem>,
        total: Int,
        bayar: Int,
        kembalian: Int,
        status: String
    ): String {
        val rupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale("in", "ID"))
        val builder = StringBuilder()
        builder.append("=== BIOSKOP XXI STIKOM Cinema ===\n")
        builder.append(dateFormat.format(Date()))
        builder.append("\n------------------------------\n")
        orderItems.forEach {
            builder.append("${it.name}\n")
            builder.append("${it.qty} x ${rupiah.format(it.price)} = ${rupiah.format(it.subtotal())}\n")
        }
        builder.append("------------------------------\n")
        builder.append("Status    : $status\n")
        builder.append("Total     : ${rupiah.format(total)}\n")
        builder.append("Bayar     : ${rupiah.format(bayar)}\n")
        builder.append("Kembalian : ${rupiah.format(kembalian)}\n")
        builder.append("------------------------------\n")
        builder.append("Selamat menonton\n")
        return builder.toString()
    }
}