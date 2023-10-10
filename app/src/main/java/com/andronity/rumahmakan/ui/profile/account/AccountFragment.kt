package com.andronity.rumahmakan.ui.profile.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.R
import com.andronity.rumahmakan.adapter.ProfileAdapter
import com.andronity.rumahmakan.databinding.FragmentAccountBinding
import com.andronity.rumahmakan.model.dummy.ProfileModel


class AccountFragment : Fragment() , ProfileAdapter.ItemAdapterCallback {
        private lateinit var binding: FragmentAccountBinding
        private var menuArrary : ArrayList<ProfileModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDummy()

        var adapter = ProfileAdapter(menuArrary, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rvProfileAc.layoutManager = layoutManager
        binding.rvProfileAc.adapter = adapter
    }

    fun initDummy(){
        menuArrary = ArrayList()
        menuArrary.add(ProfileModel("Edit Profile"))
        menuArrary.add(ProfileModel("Home Address"))
        menuArrary.add(ProfileModel("Security"))
        menuArrary.add(ProfileModel("Payments"))

    }

    override fun onClick(v: View, data: ProfileModel) {
        Toast.makeText(context, "Ini Menu Yang Di Klik" + data.title, Toast.LENGTH_SHORT).show()
    }


}