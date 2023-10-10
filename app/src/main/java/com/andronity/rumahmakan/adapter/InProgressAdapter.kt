package com.andronity.rumahmakan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.databinding.ItemInprogressBinding
import com.andronity.rumahmakan.model.response.transaction.DataItem
import com.andronity.rumahmakan.utils.Helpers.formatPrice
import com.bumptech.glide.Glide


class InProgressAdapter(
    private val listData: List<DataItem?>?,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<InProgressAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemInprogressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listData!![position]?.let { holder.bind(it, itemAdapterCallback) }
    }

    override fun getItemCount(): Int {
        return listData?.size!!
    }

    class ViewHolder(private val binding: ItemInprogressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: DataItem, itemAdapterCallback: ItemAdapterCallback
        ) {
            itemView.apply {
                binding.tvTitle.text = data.food.name
                binding.tvPrice.formatPrice(data.food.price.toString())

                Glide.with(context).load(data.food.picturePath).into(binding.ivPoster)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: DataItem)
    }
}