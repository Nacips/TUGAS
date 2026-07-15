package com.example.restoran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restoran.databinding.ItemMinumanBinding
import com.example.restoran.model.MinumanItem
import java.text.NumberFormat
import java.util.Locale

class MinumanAdapter(
    private val items: List<MinumanItem>,
    private val onAddClicked: (MinumanItem) -> Unit
) : RecyclerView.Adapter<MinumanAdapter.MinumanViewHolder>() {

    inner class MinumanViewHolder(val binding: ItemMinumanBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinumanViewHolder {
        // Menggunakan ItemMinumanBinding secara konsisten
        val binding = ItemMinumanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MinumanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MinumanViewHolder, position: Int) {
        val item = items[position]
        val rupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))

        // Mengatur data ke tampilan berdasarkan ID yang ada di item_minuman.xml
        holder.binding.tvMinumanName.text = item.nama
        holder.binding.tvMinumanCategory.text = item.kategori
        holder.binding.tvMinumanPrice.text = rupiah.format(item.harga)

        // Aksi ketika tombol tambah diklik
        holder.binding.btnAddMinuman.setOnClickListener {
            onAddClicked(item)
        }
    }

    override fun getItemCount(): Int = items.size
}