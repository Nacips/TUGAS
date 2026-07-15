package com.example.restoran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restoran.databinding.ItemMakananBinding
import com.example.restoran.model.MakananItem
import java.text.NumberFormat
import java.util.Locale

class MakananAdapter(
    private val items: List<MakananItem>,
    private val onAddClicked: (MakananItem) -> Unit
) : RecyclerView.Adapter<MakananAdapter.MakananViewHolder>() {

    inner class MakananViewHolder(val binding: ItemMakananBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakananViewHolder {
        val binding = ItemMakananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MakananViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MakananViewHolder, position: Int) {
        val item = items[position]
        val rupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))

        // Menyesuaikan ID view sesuai dengan layout item_makanan.xml
        holder.binding.tvMakananName.text = item.nama
        holder.binding.tvMakananCategory.text = item.kategori
        holder.binding.tvMakananPrice.text = rupiah.format(item.harga)

        holder.binding.btnAddMakanan.setOnClickListener {
            onAddClicked(item)
        }
    }

    override fun getItemCount(): Int = items.size
}