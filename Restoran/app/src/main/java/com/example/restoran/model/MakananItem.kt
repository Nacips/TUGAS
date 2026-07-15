package com.example.restoran.model

data class MakananItem(
    val id: Int,
    val nama: String,
    val harga: Int,
    val kategori: String = "Makanan"
)