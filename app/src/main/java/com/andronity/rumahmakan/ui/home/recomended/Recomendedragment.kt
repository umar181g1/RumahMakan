package com.andronity.rumahmakan.ui.home.recomended

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.adapter.NewStateAdapter
import com.andronity.rumahmakan.databinding.FragmentRecomendedragmentBinding
import com.andronity.rumahmakan.model.response.home.DataItem
import com.andronity.rumahmakan.ui.detail.DetailActivity


class Recomendedragment : Fragment(), NewStateAdapter.ItemAdapterCallback {

    private lateinit var binding: FragmentRecomendedragmentBinding
    private var recommendedList: ArrayList<DataItem> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecomendedragmentBinding.inflate(inflater, container,false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recommendedList = arguments?.getParcelableArrayList("data")!!

        var adapter = NewStateAdapter(recommendedList, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)

        binding.rvList.layoutManager = layoutManager
        binding.rvList.adapter = adapter

    }



    override fun onClick(v: View, data: DataItem) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}