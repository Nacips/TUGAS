package com.example.restoran.model

data class MinumanItem(
    val id: Int,
    val nama: String,
    val harga: Int,
    val kategori: String = "Minuman"
)