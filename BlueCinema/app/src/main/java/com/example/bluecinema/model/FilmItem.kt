package com.example.bluecinema.model

data class FilmItem(
    val id: Int,
    val title: String,
    val studio: String,
    val schedule: String,
    val price: Int,
    val imageRes: Int,
    val isOngoing: Boolean
)