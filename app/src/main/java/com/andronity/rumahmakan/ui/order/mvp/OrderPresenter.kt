package com.andronity.rumahmakan.ui.order.mvp

import com.andronity.rumahmakan.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class OrderPresenter(private val view: OrderContract.View) : OrderContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getTransaction() {
        view.showLoading()
        val disposable = Api.getInstance().getApi()!!.transaction().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onTransactionSuccess(it1) }
                } else {
                    it.meta?.let { it1 -> view.onTransactionFailed(it1.message) }
                }

            }
        if (disposable != null) {
            mCompositeDisposable!!.add(disposable)
        }
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}