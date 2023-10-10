package com.andronity.rumahmakan.ui.order.mvp

import com.andronity.rumahmakan.base.BasePresenter
import com.andronity.rumahmakan.base.BaseView
import com.andronity.rumahmakan.model.response.transaction.ResponseTransaction
import com.andronity.rumahmakan.ui.home.mvp.HomeContract

interface OrderContract {
    interface View : BaseView {
        fun onTransactionSuccess(responseTransaction: ResponseTransaction)
        fun onTransactionFailed(message: String)

    }

    interface Presenter : HomeContract, BasePresenter {
        fun getTransaction()

    }
}