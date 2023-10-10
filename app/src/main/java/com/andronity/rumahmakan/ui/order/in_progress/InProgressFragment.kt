package com.andronity.rumahmakan.ui.order.in_progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andronity.rumahmakan.adapter.InProgressAdapter
import com.andronity.rumahmakan.databinding.FragmentInProgressBinding
import com.andronity.rumahmakan.model.response.transaction.DataItem


class InProgressFragment : Fragment(), InProgressAdapter.ItemAdapterCallback {
    private lateinit var binding: FragmentInProgressBinding
    private lateinit var adapter: InProgressAdapter
    private var inprogressList: ArrayList<DataItem> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        inprogressList = arguments?.getParcelableArrayList("data")!!

        if (inprogressList.isNotEmpty()) {
            adapter = InProgressAdapter(inprogressList, this)
            val layoutManager = LinearLayoutManager(activity)
            binding.apply {
                rcList.layoutManager = layoutManager
                rcList.adapter = adapter
            }
        }

    }

    override fun onClick(v: View, data: DataItem) {
        Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
    }


}