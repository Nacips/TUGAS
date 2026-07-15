package com.example.restoran.util

import com.example.restoran.model.OrderItem
import java.text.NumberFormat
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
        val sb = StringBuilder()

        sb.append("********** RESTORAN KITA **********\n")
        sb.append("Jln. Stikom Poltek No. 1, Cirebon\n")
        sb.append("--------------------------------------------------\n")

        // Daftar Pesanan
        orderItems.forEach { item ->
            val subtotal = item.price * item.qty
            sb.append("${item.name}\n")
            sb.append("${item.qty} x ${rupiah.format(item.price)} = ${rupiah.format(subtotal)}\n")
        }

        sb.append("--------------------------------------------------\n")
        sb.append("Total Tagihan : ${rupiah.format(total)}\n")
        sb.append("Uang Bayar    : ${rupiah.format(bayar)}\n")
        sb.append("Kembalian     : ${rupiah.format(kembalian)}\n")
        sb.append("Status        : $status\n")
        sb.append("--------------------------------------------------\n")
        sb.append("Terima Kasih Atas Kunjungan Anda!\n")
        sb.append("Selamat Menikmati Hidangan Kami ✨\n")
        sb.append("************************************")

        return sb.toString()
    }
}