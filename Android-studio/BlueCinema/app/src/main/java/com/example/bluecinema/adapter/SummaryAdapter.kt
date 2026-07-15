package com.example.bluecinema.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecinema.databinding.ItemSummaryBinding
import com.example.bluecinema.model.SummaryItem
import com.example.bluecinema.util.MoneyHelper

class SummaryAdapter(
    private val items: MutableList<SummaryItem>,
    private val onUpdate: () -> Unit
) : RecyclerView.Adapter<SummaryAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: ItemSummaryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemSummaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = items[position]

        with(holder.binding) {

            // ICON BERDASARKAN KATEGORI
            val icon = when (item.category.lowercase()) {

                "film" -> "🎬"

                "snack" -> "🍿"

                "minuman" -> "🥤"

                else -> "🛒"
            }

            // NAMA ITEM
            tvSummaryName.text =
                "$icon ${item.name}"

            // INFO ITEM
            tvSummaryInfo.text =
                "${item.qty} x ${
                    MoneyHelper.toRupiah(item.price)
                }"

            // SUBTOTAL
            tvSummarySubtotal.text =
                MoneyHelper.toRupiah(item.subtotal())

            // QTY
            tvQty.text =
                item.qty.toString()

            // BUTTON PLUS
            btnPlus.setOnClickListener {

                item.qty += 1

                notifyItemChanged(position)

                onUpdate()
            }

            // BUTTON MINUS
            btnMinus.setOnClickListener {

                if (item.qty > 1) {

                    item.qty -= 1

                    notifyItemChanged(position)

                } else {

                    items.removeAt(position)

                    notifyItemRemoved(position)

                    notifyItemRangeChanged(
                        position,
                        items.size
                    )
                }

                onUpdate()
            }

            // ANIMASI CARD
            root.alpha = 0f

            root.animate()
                .alpha(1f)
                .setDuration(250)
                .start()
        }
    }

    override fun getItemCount(): Int =
        items.size
}