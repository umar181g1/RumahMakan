package com.andronity.rumahmakan.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.andronity.rumahmakan.ui.profile.account.AccountFragment
import com.andronity.rumahmakan.ui.profile.foodmarker.FoodmarketFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Account"
            1 -> "FoodMarket"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = AccountFragment()
                return fragment
            }
            1 -> {
                fragment = FoodmarketFragment()
                return fragment
            }
            else -> {
                fragment = AccountFragment()
                return fragment
            }
        }
    }
}