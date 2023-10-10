package com.andronity.rumahmakan.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.andronity.rumahmakan.R
import com.andronity.rumahmakan.databinding.FragmentDetailFoodBinding
import com.andronity.rumahmakan.model.response.home.DataItem
import com.andronity.rumahmakan.utils.Helpers.formatPrice
import com.bumptech.glide.Glide

class DetailFoodFragment : Fragment() {
    private lateinit var binding: FragmentDetailFoodBinding
    private lateinit var data: DataItem
    private lateinit var bundle: Bundle


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDetailFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        data = requireActivity().intent.getParcelableExtra("data")!!
        initView(data)



        binding.btnOrderNow.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_payment, bundle)
        }
    }

    private fun initView(data: DataItem) {
        bundle = bundleOf("data" to data)
        data.let {
            Glide.with(requireContext()).load(it.picturePath).into(binding.ivPoster)

            binding.apply {
                tvTitle.text = it.name
                tvDesc.text = it.description
                tvIngredients.text = it.ingredients
                tvTotal.formatPrice(it.price.toString())
            }
        }
        Log.d("tapam", data.toString())


    }


}