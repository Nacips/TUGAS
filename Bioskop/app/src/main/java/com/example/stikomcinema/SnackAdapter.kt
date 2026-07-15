package com.example.stikomcinema

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stikomcinema.databinding.ItemFoodBinding
import com.example.stikomcinema.model.FoodItem
import java.text.NumberFormat
import java.util.Locale
class SnackAdapter(
    private val items: List<FoodItem>,
    private val onAddClicked: (FoodItem) -> Unit
) : RecyclerView.Adapter<SnackAdapter.FoodViewHolder>() {
    inner class FoodViewHolder(val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = items[position]
        val rupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        holder.binding.tvFoodName.text = item.name
        holder.binding.tvFoodCategory.text = item.category
        holder.binding.tvFoodPrice.text = rupiah.format(item.price)
        holder.binding.btnAddFood.setOnClickListener {
            onAddClicked(item)
        }
    }
    override fun getItemCount(): Int = items.size
}