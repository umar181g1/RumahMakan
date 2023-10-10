package com.andronity.rumahmakan.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.andronity.rumahmakan.model.response.transaction.DataItem
import com.andronity.rumahmakan.ui.order.in_progress.InProgressFragment
import com.andronity.rumahmakan.ui.order.past_orders.PastOrdersFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var inprogressList: ArrayList<DataItem> = ArrayList()
    var pastOrderList: ArrayList<DataItem> = ArrayList()


    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "In Progress"
            1 -> "Past Orders"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        return when (position) {
            0 -> {
                fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PastOrdersFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastOrderList)
                fragment.arguments = bundle
                return fragment
            }

            else -> {
                fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }


    fun setData(inprogressListParms:ArrayList<DataItem>, pastordersListParms:ArrayList<DataItem>){
        inprogressList = inprogressListParms
        pastOrderList = pastordersListParms
    }


}