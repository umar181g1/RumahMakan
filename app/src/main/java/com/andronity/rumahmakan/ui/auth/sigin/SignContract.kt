package com.andronity.rumahmakan.ui.auth.sigin

import com.andronity.rumahmakan.base.BasePresenter
import com.andronity.rumahmakan.base.BaseView
import com.andronity.rumahmakan.model.response.login.ResponseLogin

interface SignContract {

    interface View: BaseView {
        fun onLoginSuccess(loginResponseLogin: ResponseLogin)
        fun onLoginFailed(message:String)

    }

    interface Presenter : SignContract, BasePresenter {
        fun subimtLogin(email:String, password:String)

    }
}