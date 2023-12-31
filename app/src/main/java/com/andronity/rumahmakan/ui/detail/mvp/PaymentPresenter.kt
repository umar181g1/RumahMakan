package com.andronity.rumahmakan.ui.detail.mvp

import android.view.View
import com.andronity.rumahmakan.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PaymentPresenter(private val view: PaymentContract.View) : PaymentContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }


    override fun getCheckout(
        foodId: String, userId: String, quantity: String, total: String, viewParms: View
    ) {
        view.showLoading()
        val disposable = Api.getInstance().getApi()!!.checkout(
            foodId, userId, quantity, total, "DELIVERED"
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe (
            {
                view.dismissLoading()

                if (it.meta?.status.equals("success",true)){
                    it.data?.let { it1 -> view.onCheckoutSuccess(it1, viewParms) }
                } else {
                    it.meta?.message?.let { it1 -> view.onCheckoutFailed(it1) }
                }
            },
            {
                view.dismissLoading()
                view.onCheckoutFailed(it.message.toString())
            }
        )
        mCompositeDisposable!!.add(disposable)

    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()

    }
}