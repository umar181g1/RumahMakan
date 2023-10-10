package com.andronity.rumahmakan.ui.auth.sigup.mvp

import android.net.Uri
import com.andronity.rumahmakan.base.BasePresenter
import com.andronity.rumahmakan.base.BaseView
import com.andronity.rumahmakan.model.request.RegisterRequest
import com.andronity.rumahmakan.model.response.login.ResponseLogin

interface SignupContract {
    interface View: BaseView {
        fun onRegisterSuccess(loginResponseLogin: ResponseLogin , view: android.view.View)
        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message:String)

    }

    interface Presenter : SignupContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
        fun submitPhoto(filePath:Uri, view:android.view.View)

    }
}