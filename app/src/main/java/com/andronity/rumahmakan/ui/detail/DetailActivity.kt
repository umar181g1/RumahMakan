package com.andronity.rumahmakan.ui.detail

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.andronity.rumahmakan.R
import com.andronity.rumahmakan.databinding.ActivityDetailBinding
import kotlin.math.log

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.detailHostFragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it)
            navController.setGraph(navController.graph, bundle);
            Log.d("tapam", bundle.toString())
        }



    }


    fun toolbarPayment() {
        binding.includeToolbar.toolbar.visibility = View.VISIBLE
        binding.includeToolbar.toolbar.title = "Payment"
        binding.includeToolbar.toolbar.subtitle = "You deserve better meal"
        binding.includeToolbar.toolbar.navigationIcon =
            resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        binding.includeToolbar.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarDetail() {
        binding.includeToolbar.toolbar.visibility = View.GONE
    }
}