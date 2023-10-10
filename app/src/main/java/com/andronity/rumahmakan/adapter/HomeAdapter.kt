package com.andronity.rumahmakan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.databinding.ItemHomeHorizontalBinding
import com.andronity.rumahmakan.model.response.home.DataItem
import com.bumptech.glide.Glide

class HomeAdapter (private val listData : List<DataItem?>?, private  val itemAdapterCallback : ItemAdapterCallback) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding = ItemHomeHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        listData!![position]?.let { holder.bind(it,itemAdapterCallback) }
    }

    override fun getItemCount(): Int {
        return listData?.size!!
    }

    class ViewHolder (private val binding: ItemHomeHorizontalBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: DataItem, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                binding.tvTitle.text = data.name
                binding.rbFood.rating = data.rate!!.toFloat() ?: 0f

                Glide.with(context)
                    .load(data.picturePath)
                    .into(binding.ivPoster)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback{
        fun  onClick(v:View ,data:DataItem)
    }
}