package com.example.bluecinema.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecinema.databinding.ItemFilmBinding
import com.example.bluecinema.model.FilmItem
import com.example.bluecinema.util.MoneyHelper

class FilmAdapter(
    private val items: List<FilmItem>,
    private val onClick: (FilmItem) -> Unit
) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        with(holder.binding) {

            // FOTO FILM
            imgFilm.setImageResource(item.imageRes)

            // JUDUL
            tvFilmTitle.text = item.title

            // STUDIO + JAM
            tvFilmInfo.text = "${item.studio} • ${item.schedule}"

            // HARGA
            tvFilmPrice.text = MoneyHelper.toRupiah(item.price)

            // BUTTON
            btnAddFilm.text = "🎬 Pilih Film"

            btnAddFilm.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}