package com.andronity.rumahmakan.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andronity.rumahmakan.R
import com.andronity.rumahmakan.RumahMakan
import com.andronity.rumahmakan.adapter.HomeAdapter
import com.andronity.rumahmakan.databinding.FragmentHomeBinding
import com.andronity.rumahmakan.model.response.home.DataItem
import com.andronity.rumahmakan.model.response.home.ResponseFood
import com.andronity.rumahmakan.model.response.login.User
import com.andronity.rumahmakan.ui.detail.DetailActivity
import com.andronity.rumahmakan.ui.home.mvp.HomeContract
import com.andronity.rumahmakan.ui.home.mvp.HomePresenter
import com.bumptech.glide.Glide
import com.google.gson.Gson

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View {
    private var newState: ArrayList<DataItem> = ArrayList()
    private var popularLis: ArrayList<DataItem> = ArrayList()
    private var recommendedList: ArrayList<DataItem> = ArrayList()
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var presenter: HomePresenter
    private lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = HomePresenter(this)
        presenter.getHome()


    }

    @SuppressLint("InflateParams")
    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        val user = RumahMakan.getApp().getUser()
        val userResponse = Gson().fromJson(user, User::class.java)

        if (!userResponse.profilePhotoUrl?.isEmpty()!!){
            Glide.with(requireActivity())
                .load(userResponse.profilePhotoUrl)
                .into(homeBinding.ivProfile)
        }
    }


    override fun onClick(v: View, data: DataItem) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }

    override fun onHomeSuccess(responseFood: ResponseFood) {
        for (a in responseFood.data!!.indices) {
            var items: List<String> = responseFood.data[a]!!.types!!.split(",")
            for (x in items.indices) {
                if (items[x].equals("new_food", true)) {
                    responseFood.data[a]?.let { newState.add(it) }
                } else if (items[x].equals("recommended", true)) {
                    responseFood.data[a]?.let { recommendedList.add(it) }

                } else if (items[x].equals("popular", true)) {
                    responseFood.data[a]?.let { popularLis.add(it) }

                }
            }
        }


        val adapter = HomeAdapter(responseFood.data, this)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        homeBinding.rvHome.layoutManager = layoutManager
        homeBinding.rvHome.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        sectionPagerAdapter.setData(newState,recommendedList,popularLis)
        homeBinding.viewPager.adapter = sectionPagerAdapter
        homeBinding.tablayout.setupWithViewPager(homeBinding.viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }
}