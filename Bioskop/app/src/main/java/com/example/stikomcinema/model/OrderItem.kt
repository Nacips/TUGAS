package com.example.stikomcinema.model

data class OrderItem(
    val name: String,
    val price: Int,
    var qty: Int = 1,
    val type: String
) {
    fun subtotal(): Int = price * qty
}