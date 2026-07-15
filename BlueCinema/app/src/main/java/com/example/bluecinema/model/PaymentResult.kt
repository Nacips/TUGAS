package com.example.bluecinema.model

data class PaymentResult(
    val total: Int,
    val paid: Int,
    val change: Int,
    val status: String
)