package com.andronity.rumahmakan.ui.order.past_orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andronity.rumahmakan.adapter.PastOrderAdapter
import com.andronity.rumahmakan.databinding.FragmentPastOrdersBinding
import com.andronity.rumahmakan.model.response.transaction.DataItem


class PastOrdersFragment : Fragment(), PastOrderAdapter.ItemAdapterCallback {
    private lateinit var binding: FragmentPastOrdersBinding
    private lateinit var adapter: PastOrderAdapter
    private var pastordersList: ArrayList<DataItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPastOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pastordersList = arguments?.getParcelableArrayList("data")!!

        if (pastordersList.isNotEmpty()) {
            adapter = PastOrderAdapter(pastordersList, this)
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