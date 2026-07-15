package com.example.bluecinema.util

import com.example.bluecinema.R
import com.example.bluecinema.model.FilmItem
import com.example.bluecinema.model.SnackItem

object DataDummy {

    fun getAllFilms(): List<FilmItem> {
        return listOf(

            // 🎬 ONGOING FILM
            FilmItem(1, "Elio", "Studio 1", "14:00", 55000, R.drawable.elio, true),
            FilmItem(2, "13 Bom di Jakarta", "Studio 2", "13:15", 50000, R.drawable.bomjakarta, true),
            FilmItem(3, "Zootopia 2", "Studio 3", "13:45", 45000, R.drawable.zootopia2, true),

            // 🎞️ MENU / NON-ONGOING
            FilmItem(11, "Suzume (Suzume no Tojimari)", "Studio 1", "19:00", 55000, R.drawable.suzume, false),
            FilmItem(12, "Frozen II", "Studio 2", "21:15", 50000, R.drawable.frozen, false),
            FilmItem(13, "Psycho Pass", "Studio 3", "17:45", 45000, R.drawable.psychopass, false),
            FilmItem(14, "Chainsaw Man – The Movie: Reze Arc", "Studio 4", "20:30", 50000, R.drawable.reze, false)
        )
    }

    fun getSnacks(): List<SnackItem> {
        return listOf(
            SnackItem(21, "Popcorn", "Snack", 30000, R.drawable.popcorn),
            SnackItem(22, "Nachos", "Snack", 28000, R.drawable.nachos),
            SnackItem(23, "Cola", "Minuman", 18000, R.drawable.cola),
            SnackItem(24, "Air Mineral", "Minuman", 12000, R.drawable.mineral),
            SnackItem(25, "Burger", "Snack", 30000, R.drawable.burger),
            SnackItem(26, "Hotdog", "Snack", 28000, R.drawable.hotdog),
            SnackItem(27, "Coffee", "Minuman", 18000, R.drawable.coffee),
            SnackItem(28, "Fanta", "Minuman", 12000, R.drawable.fanta)
        )
    }
}