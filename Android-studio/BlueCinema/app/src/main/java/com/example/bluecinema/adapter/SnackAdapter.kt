package com.example.bluecinema.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecinema.databinding.ItemSnackBinding
import com.example.bluecinema.model.SnackItem
import com.example.bluecinema.util.MoneyHelper

class SnackAdapter(
    private val items: List<SnackItem>,
    private val onClick: (SnackItem) -> Unit
) : RecyclerView.Adapter<SnackAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSnackBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemSnackBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        with(holder.binding) {

            // FOTO SNACK
            imgSnack.setImageResource(item.imageRes)

            // NAMA
            tvSnackName.text = item.name

            // KATEGORI
            tvSnackCategory.text = item.category

            // HARGA
            tvSnackPrice.text = MoneyHelper.toRupiah(item.price)

            // BUTTON
            btnAddSnack.text = "🍿 Tambah"

            btnAddSnack.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}