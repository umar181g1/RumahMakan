package com.andronity.rumahmakan.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.andronity.rumahmakan.R
import com.andronity.rumahmakan.databinding.FragmentOrdersBinding
import com.andronity.rumahmakan.model.response.transaction.DataItem
import com.andronity.rumahmakan.model.response.transaction.ResponseTransaction
import com.andronity.rumahmakan.ui.order.mvp.OrderContract
import com.andronity.rumahmakan.ui.order.mvp.OrderPresenter

class OrderFragment : Fragment(), OrderContract.View {

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var presenter: OrderPresenter
    lateinit var progressDialog: Dialog
    var inprogressList: ArrayList<DataItem> = ArrayList()
    var pastorderList: ArrayList<DataItem> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()

    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

    }

    override fun onTransactionSuccess(responseTransaction: ResponseTransaction) {
        if (responseTransaction.data.isNotEmpty()) {
            binding.apply {
                llEmpty.visibility = View.VISIBLE
                llTab.visibility = View.GONE
                includeToolbar.toolbar.visibility = View.GONE
            }
        } else {

            for (a in responseTransaction.data.indices) {
                if (responseTransaction.data[a].status.equals(
                        "ON_DELIVERY",
                        true
                    ) || responseTransaction.data[a].status.equals("PENDING'", true)
                ) {
                    inprogressList.add(responseTransaction.data[a])
                } else if (responseTransaction.data[a].status.equals(
                        "DELIVERED",
                        true
                    ) || responseTransaction.data[a].status.equals(
                        "CANCELLED'",
                        true
                    ) || responseTransaction.data[a].status.equals("SUCCESS'", true)
                ) {
                    pastorderList.add(responseTransaction.data[a])
                }
            }

            val sectionPagerAdapter = SectionPagerAdapter(
                childFragmentManager
            )
            sectionPagerAdapter.setData(inprogressList, pastorderList)
            binding.apply {
                viewPager.adapter = sectionPagerAdapter
                tabLayout.setupWithViewPager(viewPager)
            }

        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }


}