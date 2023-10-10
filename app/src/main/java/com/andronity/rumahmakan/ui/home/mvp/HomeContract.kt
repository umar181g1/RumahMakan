package com.andronity.rumahmakan.ui.home.mvp

import com.andronity.rumahmakan.base.BasePresenter
import com.andronity.rumahmakan.base.BaseView
import com.andronity.rumahmakan.model.response.home.ResponseFood

interface HomeContract {
    interface View: BaseView {
        fun onHomeSuccess(responseFood: ResponseFood)
        fun onHomeFailed(message:String)

    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()

    }
}