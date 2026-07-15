package com.example.stikomcinema

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stikomcinema.databinding.ItemOrderBinding
import com.example.stikomcinema.model.OrderItem
import java.text.NumberFormat
import java.util.Locale
class OrderAdapter(
    private val items: List<OrderItem>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    inner class OrderViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = items[position]
        val rupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        holder.binding.tvOrderName.text = item.name
        holder.binding.tvOrderInfo.text = "${item.qty} x ${rupiah.format(item.price)}"
        holder.binding.tvSubtotal.text = rupiah.format(item.subtotal())
    }
    override fun getItemCount(): Int = items.size
}