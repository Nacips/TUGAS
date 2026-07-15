package com.example.bluecinema.model

data class SummaryItem(
    val id: Int,
    val name: String,
    val category: String,
    val price: Int,
    val imageRes: Int,
    var qty: Int = 1
) {
    fun subtotal(): Int = price * qty
}

