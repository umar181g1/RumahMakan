package com.andronity.rumahmakan.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.adapter.NewStateAdapter
import com.andronity.rumahmakan.databinding.FragmentNewStateragmentBinding
import com.andronity.rumahmakan.model.dummy.VerticalModel
import com.andronity.rumahmakan.model.response.home.DataItem
import com.andronity.rumahmakan.ui.detail.DetailActivity


class NewStateragment : Fragment() , NewStateAdapter.ItemAdapterCallback {
    private lateinit var binding: FragmentNewStateragmentBinding
    private var  newStateList : ArrayList<DataItem> = ArrayList()
    private var foodList: ArrayList<VerticalModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentNewStateragmentBinding.inflate(inflater, container,false)
        return  binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newStateList = arguments?.getParcelableArrayList("data")!!


        var adapter = NewStateAdapter(newStateList, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)

        binding.rvList.layoutManager = layoutManager
        binding.rvList.adapter = adapter

    }



    override fun onClick(v: View, data: DataItem) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}