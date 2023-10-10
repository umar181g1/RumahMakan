package com.andronity.rumahmakan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.databinding.ItemHomeHorizontalBinding
import com.andronity.rumahmakan.databinding.ItemMenuProfileBinding
import com.andronity.rumahmakan.model.dummy.HomeModel
import com.andronity.rumahmakan.model.dummy.ProfileModel
import com.bumptech.glide.Glide

class ProfileAdapter (private val listData : List<ProfileModel>, private  val itemAdapterCallback : ItemAdapterCallback) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        val binding = ItemMenuProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position],itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return  listData.size
    }

    class ViewHolder (private val binding: ItemMenuProfileBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: ProfileModel, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                binding.tvTitle.text = data.title

//                Glide.with(context)
//                    .load(data.src)
//                    .into(binding.ivPoster)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback{
        fun  onClick(v:View ,data:ProfileModel)
    }
}