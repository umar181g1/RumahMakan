package com.andronity.rumahmakan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.databinding.ItemPastorderBinding
import com.andronity.rumahmakan.model.response.transaction.DataItem
import com.andronity.rumahmakan.utils.Helpers.convertLongToTime
import com.andronity.rumahmakan.utils.Helpers.formatPrice
import com.bumptech.glide.Glide

class PastOrderAdapter(
    private val listData: List<DataItem?>?, private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<PastOrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPastorderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listData!![position]?.let { holder.bind(it, itemAdapterCallback) }
    }

    override fun getItemCount(): Int {
        return listData?.size!!
    }

    class ViewHolder(private val binding: ItemPastorderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: DataItem, itemAdapterCallback: ItemAdapterCallback
        ) {
            itemView.apply {
                binding.tvTitle.text = data.food.name
                binding.tvPrice.formatPrice(data.food.price.toString())
                binding.tvDate.text = data.food.createdAt.convertLongToTime("MMM dd, HH.mm")

                Glide.with(context).load(data.food.picturePath).into(binding.ivPoster)

                if (data.status.equals("CANCELLED", true)) {
                    binding.tvCancelled.visibility = View.VISIBLE
                }


                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: DataItem)
    }
}