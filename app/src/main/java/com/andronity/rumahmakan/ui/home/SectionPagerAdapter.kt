package com.andronity.rumahmakan.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.andronity.rumahmakan.model.response.home.DataItem
import com.andronity.rumahmakan.ui.home.newtaste.NewStateragment
import com.andronity.rumahmakan.ui.home.popular.PopularFragment
import com.andronity.rumahmakan.ui.home.recomended.Recomendedragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var newState: ArrayList<DataItem> = ArrayList()
    private var popularLis: ArrayList<DataItem> = ArrayList()
    private var recommendedList: ArrayList<DataItem> = ArrayList()
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "New State"
            1 -> "Popular"
            2 -> "Recomended"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = NewStateragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data" , newState)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PopularFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data" , popularLis)
                fragment.arguments = bundle
                return fragment
            }
            2 -> {
                fragment = Recomendedragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data" , recommendedList)
                fragment.arguments = bundle
                return fragment
            }

            else -> {
                fragment = NewStateragment()
                return fragment
            }
        }
    }

    fun setData(newStateList : ArrayList<DataItem>, recommendedListPa : ArrayList<DataItem>, popularListPa : ArrayList<DataItem>){
        newState  = newStateList
        recommendedList = recommendedListPa
        popularLis = popularListPa

    }


}