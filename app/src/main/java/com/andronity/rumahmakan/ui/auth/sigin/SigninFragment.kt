package com.andronity.rumahmakan.ui.auth.sigin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.andronity.rumahmakan.MainActivity
import com.andronity.rumahmakan.R
import com.andronity.rumahmakan.RumahMakan
import com.andronity.rumahmakan.databinding.FragmentSigninBinding
import com.andronity.rumahmakan.model.response.login.ResponseLogin
import com.andronity.rumahmakan.ui.auth.AuthActivity
import com.google.gson.Gson


class SigninFragment : Fragment(), SignContract.View {
    private lateinit var fragmentSigninBinding: FragmentSigninBinding
    private lateinit var presenter: SiginPresenter
    private lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSigninBinding = FragmentSigninBinding.inflate(inflater, container, false)
        return fragmentSigninBinding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SiginPresenter(this)
        if (RumahMakan.getApp().getToken()?.isNotEmpty() == true) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }
        initview()

        fragmentSigninBinding.btnSignup.setOnClickListener {
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request", 2)
            startActivity(signup)
        }

        fragmentSigninBinding.btnSignin.setOnClickListener {
            val email = fragmentSigninBinding.etEmail.text.toString()
            val password = fragmentSigninBinding.etPassword.text.toString()

            if (email.isEmpty()) {
                fragmentSigninBinding.etEmail.error = "Silahkan Masukan Email Anda"
                fragmentSigninBinding.etEmail.requestFocus()
            } else if (password.isEmpty()) {
                fragmentSigninBinding.etPassword.error = "Silahkan Masukan Password Anda"
                fragmentSigninBinding.etPassword.requestFocus()
            } else {
                presenter.subimtLogin(email, password)
            }
        }
    }



    override fun onLoginSuccess(login: ResponseLogin) {
        login.accessToken?.let { RumahMakan.getApp().setToken(it) }
        val gson = Gson()
        val json = gson.toJson(login.user)
        RumahMakan.getApp().setUser(json)
        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()

    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun initview() {
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