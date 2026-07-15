package com.example.restoran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restoran.databinding.ItemOrderBinding // Perbaikan import binding
import com.example.restoran.model.OrderItem
import java.text.NumberFormat
import java.util.Locale

class OrderAdapter(
    private val items: MutableList<OrderItem>,
    private val onUpdate: () -> Unit // Memberikan nama pada parameter fungsi
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

        // Menampilkan data dasar
        holder.binding.tvOrderName.text = item.name
        holder.binding.tvOrderInfo.text = "1 x ${rupiah.format(item.price)}"
        holder.binding.tvOrderQty.text = item.qty.toString()
        holder.binding.tvSubtotal.text = rupiah.format(item.subtotal())

        // Fitur TAMBAH (+)
        holder.binding.btnPlus.setOnClickListener {
            item.qty++
            notifyItemChanged(position)
            onUpdate() // Memberitahu MainActivity untuk hitung ulang Total
        }

        // Fitur KURANG (-)
        holder.binding.btnMinus.setOnClickListener {
            if (item.qty > 1) {
                item.qty--
                notifyItemChanged(position)
            } else {
                // Jika jumlah 1 dikurangi lagi, maka item dihapus dari keranjang
                items.removeAt(position)
                notifyDataSetChanged()
            }
            onUpdate() // Memberitahu MainActivity untuk hitung ulang Total
        }
    }

    override fun getItemCount(): Int = items.size
}