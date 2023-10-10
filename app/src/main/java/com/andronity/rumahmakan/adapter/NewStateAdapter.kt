package com.andronity.rumahmakan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.databinding.ItemFoodVerticalBinding
import com.andronity.rumahmakan.model.response.home.DataItem
import com.andronity.rumahmakan.utils.Helpers.formatPrice
import com.bumptech.glide.Glide

class NewStateAdapter(
    private val listData: List<DataItem>,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<NewStateAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFoodVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }


    class ViewHolder(private val binding: ItemFoodVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                binding.tvTitle.text = data.name
                binding.tvPrice.formatPrice(data.price.toString())
                binding.rbFood.rating = data.rate?.toFloat() ?: 0f

                Glide.with(context)
                    .load(data.picturePath)
                    .into(binding.ivPoster)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: DataItem)
    }
}