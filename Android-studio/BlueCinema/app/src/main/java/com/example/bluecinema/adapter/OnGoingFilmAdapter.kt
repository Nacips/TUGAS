package com.example.bluecinema.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecinema.databinding.ItemOngoingFilmBinding
import com.example.bluecinema.model.FilmItem
import com.example.bluecinema.util.MoneyHelper

class OnGoingFilmAdapter(
    private val items: List<FilmItem>,
    private val onClick: (FilmItem) -> Unit
) : RecyclerView.Adapter<OnGoingFilmAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: ItemOngoingFilmBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOngoingFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        with(holder.binding) {

            imgFilm.setImageResource(item.imageRes)

            tvFilmTitle.text = "🎬 ${item.title}"
            tvFilmInfo.text = "📍 ${item.studio} • 🕒 ${item.schedule}"
            tvFilmPrice.text = "Mulai ${MoneyHelper.toRupiah(item.price)}"

            btnAddFilm.setOnClickListener {
                val pos = holder.adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    onClick(items[pos])
                }
            }
        }
    }

    override fun getItemCount() = items.size
}