package com.andronity.rumahmakan.ui.profile.foodmarker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.adapter.ProfileAdapter
import com.andronity.rumahmakan.databinding.FragmentFoodmarketBinding
import com.andronity.rumahmakan.model.dummy.ProfileModel


class FoodmarketFragment : Fragment(), ProfileAdapter.ItemAdapterCallback {
    private lateinit var binding: FragmentFoodmarketBinding
    private var menuArrary: ArrayList<ProfileModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodmarketBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDummy()

        var adapter = ProfileAdapter(menuArrary, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rvProfileFc.layoutManager = layoutManager
        binding.rvProfileFc.adapter = adapter
    }

    fun initDummy() {
        menuArrary = ArrayList()
        menuArrary.add(ProfileModel("Rate App"))
        menuArrary.add(ProfileModel("Help Center"))
        menuArrary.add(ProfileModel("Privacy & Policy"))
        menuArrary.add(ProfileModel("Terms & Conditions"))

    }

    override fun onClick(v: View, data: ProfileModel) {
        Toast.makeText(context, "Ini Menu Yang Di Klik" + data.title, Toast.LENGTH_SHORT).show()
    }


}