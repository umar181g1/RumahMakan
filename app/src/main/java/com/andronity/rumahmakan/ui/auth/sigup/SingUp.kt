package com.andronity.rumahmakan.ui.auth.sigup

import android.annotation.SuppressLint
import android.app.Activity
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
import com.andronity.rumahmakan.databinding.FragmentSingUpBinding
import com.andronity.rumahmakan.model.request.RegisterRequest
import com.andronity.rumahmakan.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker


class SingUp : Fragment() {
    private lateinit var singUpBinding: FragmentSingUpBinding
    var filePath: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        singUpBinding = FragmentSingUpBinding.inflate(inflater, container, false)
        return singUpBinding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()


    }

    private fun initListener() {
        singUpBinding.ivProfil.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }

        singUpBinding.btnContinue.setOnClickListener {
            val fullname = singUpBinding.etFullname.text.toString()
            val email = singUpBinding.etEmail.text.toString()
            val pass = singUpBinding.etPassword.text.toString()

            if (fullname.isEmpty()) {
                singUpBinding.etFullname.error = "Silahkan Isi FullName"
                singUpBinding.etFullname.requestFocus()
            } else if (email.isEmpty()) {
                singUpBinding.etEmail.error = "Silahkan Isi Email"
                singUpBinding.etEmail.requestFocus()
            } else if (pass.isEmpty()) {
                singUpBinding.etPassword.error = "Silahkan Isi Password"
                singUpBinding.etPassword.requestFocus()
            } else {
                val data = RegisterRequest(
                    fullname,
                    email,
                    pass,
                    pass,
                    "", "", "", "",
                    filePath
                )
                val bundle = Bundle()
                bundle.putParcelable("data", data)

                Navigation.findNavController(it)
                    .navigate(R.id.action_signup_address, bundle)

                (activity as AuthActivity).toolbarSignUpAddress()

            }


        }
    }



    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(singUpBinding.ivProfil)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()

        }
    }


}