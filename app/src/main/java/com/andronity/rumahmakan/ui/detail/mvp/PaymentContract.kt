package com.andronity.rumahmakan.ui.detail.mvp

import com.andronity.rumahmakan.base.BasePresenter
import com.andronity.rumahmakan.base.BaseView
import com.andronity.rumahmakan.model.response.checkout.ResponseCheckout

interface PaymentContract {

    interface View : BaseView {
        fun onCheckoutSuccess(responseCheckout: ResponseCheckout, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(
            foodId: String,
            userId: String,
            quantity: String,
            total: String,
            view: android.view.View
        )
    }
}