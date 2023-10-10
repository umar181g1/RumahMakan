package com.andronity.rumahmakan.ui.auth.sigup

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.andronity.rumahmakan.R
import com.andronity.rumahmakan.RumahMakan
import com.andronity.rumahmakan.databinding.FragmentSingUpAdrressBinding
import com.andronity.rumahmakan.model.request.RegisterRequest
import com.andronity.rumahmakan.model.response.login.ResponseLogin
import com.andronity.rumahmakan.ui.auth.AuthActivity
import com.andronity.rumahmakan.ui.auth.sigup.mvp.SignupContract
import com.andronity.rumahmakan.ui.auth.sigup.mvp.SignupPresenter
import com.google.gson.Gson


class SingUpAdrressFragment : Fragment(), SignupContract.View {
    private lateinit var data: RegisterRequest
    private lateinit var singUpAdrressBinding: FragmentSingUpAdrressBinding
    private lateinit var presenter: SignupPresenter
    private lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        singUpAdrressBinding = FragmentSingUpAdrressBinding.inflate(inflater, container, false)
        return singUpAdrressBinding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignupPresenter(this)

        data = arguments?.getParcelable<RegisterRequest>("data")!!

        initListener()
        initView()
    }

    private fun initListener() {
        singUpAdrressBinding.btnSignUpNow.setOnClickListener { it ->

            val phone = singUpAdrressBinding.etPhoneNumber.text.toString()
            val adress = singUpAdrressBinding.etAddress.text.toString()
            val houseNumber = singUpAdrressBinding.etHouseNumber.text.toString()
            val city = singUpAdrressBinding.etCity.text.toString()

            data.let {
                it.address = adress
                it.city = city
                it.houseNumber = houseNumber
                it.phoneNumber = phone
            }

            if (phone.isEmpty()) {
                singUpAdrressBinding.etPhoneNumber.error = "Silahkan Masukan Nomer Hp anda"
                singUpAdrressBinding.etPhoneNumber.requestFocus()
            } else if (adress.isEmpty()) {
                singUpAdrressBinding.etAddress.error = "Silahkan Masukan Alamat Anda"
                singUpAdrressBinding.etAddress.requestFocus()
            } else if (houseNumber.isEmpty()) {
                singUpAdrressBinding.etHouseNumber.error = "Silahkan Masukan Nomer Rumah Anda"
                singUpAdrressBinding.etHouseNumber.requestFocus()
            } else if (city.isEmpty()) {
                singUpAdrressBinding.etCity.error = "Silahkan Masukan Kota  Anda"
                singUpAdrressBinding.etCity.requestFocus()
            } else {
                presenter.submitRegister(data, it)
            }

        }
    }



    override fun onRegisterSuccess(loginResponseLogin: ResponseLogin, view: View) {
        loginResponseLogin.accessToken?.let { RumahMakan.getApp().setToken(it) }
        val gson = Gson()
        val json = gson.toJson(loginResponseLogin.user)
        RumahMakan.getApp().setUser(json)

        if (data.filePath == null) {
            Navigation.findNavController(view)
                .navigate(R.id.action_signup_success, null)
            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {
            presenter.submitPhoto(data.filePath!!, view)
        }

    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_signup_success, null)
        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }


}