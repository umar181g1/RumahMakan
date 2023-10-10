package com.andronity.rumahmakan.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.andronity.rumahmakan.R
import com.andronity.rumahmakan.RumahMakan
import com.andronity.rumahmakan.databinding.FragmentPaymentBinding
import com.andronity.rumahmakan.model.response.checkout.ResponseCheckout
import com.andronity.rumahmakan.model.response.home.DataItem
import com.andronity.rumahmakan.model.response.transaction.User
import com.andronity.rumahmakan.ui.detail.mvp.PaymentContract
import com.andronity.rumahmakan.ui.detail.mvp.PaymentPresenter
import com.andronity.rumahmakan.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import com.google.gson.Gson

class PaymentFragment : Fragment() , PaymentContract.View{

    private lateinit var binding: FragmentPaymentBinding
    private var total:Int = 0
    private lateinit var paymentPresenter: PaymentPresenter
    lateinit var progressDialog : Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarPayment()

        var data = arguments?.getParcelable<DataItem>("data")
        initView(data)
        initView()
        paymentPresenter = PaymentPresenter(this)

        binding.btnCheckout.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.fragmentPaymentSuccess)

        }

    }

    private fun  initView(){
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initView(data: DataItem?) {
        binding.apply {
            txtTitle.text = data?.name
            tvPrice.formatPrice(data?.price.toString())

            Glide.with(requireActivity())
                .load(data?.picturePath)
                .into(ivPoster)


            tvNameItem.text = data?.name
            tvHarga.formatPrice(data?.price.toString())

            if (data?.price.toString().isNotEmpty()){
                var totalTax = data?.price?.div(10)
                tvTax.formatPrice(totalTax.toString())

                if (data != null) {
                    total = data.price!! + totalTax!! + 50000
                }
                tvTotal.formatPrice(total.toString())

            }else{
                tvPrice.text = "IDR 0"
                tvTax.text = "IDR 0"
                tvTotal.text = "IDR 0"
                total = 0
            }

            var user = RumahMakan.getApp().getUser()
            var responUser = Gson().fromJson(user , User::class.java)


            tvName.text = responUser.name
            tvPhoneNo.text = responUser.phoneNumber
            tvAddress.text = responUser.address
            tvCity.text = responUser.city

            btnCheckout.setOnClickListener {
                paymentPresenter.getCheckout(
                    data?.id.toString(),
                    responUser.id.toString(),
                    "1",
                    total.toString(),
                    it
                )


            }

        }

    }

    override fun onCheckoutSuccess(responseCheckout: ResponseCheckout, view: View) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(responseCheckout.paymentUrl)
        startActivity(i)
        Navigation.findNavController(view).navigate(R.id.action_payment_success)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }


}