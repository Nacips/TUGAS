package com.example.stikomcinema

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stikomcinema.databinding.ItemFilmBinding
import com.example.stikomcinema.model.FilmItem
import java.text.NumberFormat
import java.util.Locale
class FilmAdapter(
    private val items: List<FilmItem>,
    private val onAddClicked: (FilmItem) -> Unit
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    inner class FilmViewHolder(val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val item = items[position]
        val rupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        holder.binding.tvFilmTitle.text = item.title
        holder.binding.tvStudio.text = item.studio
        holder.binding.tvSchedule.text = item.schedule
        holder.binding.tvFilmPrice.text = rupiah.format(item.price)
        holder.binding.btnAddFilm.setOnClickListener {
            onAddClicked(item)
        }
    }
    override fun getItemCount(): Int = items.size
}