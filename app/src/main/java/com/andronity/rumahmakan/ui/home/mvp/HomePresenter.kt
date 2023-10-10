package com.andronity.rumahmakan.ui.home.mvp

import com.andronity.rumahmakan.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter  {
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }
    override fun getHome() {
        view.showLoading()
        val disposable = Api.getInstance().getApi()!!.getFood()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onHomeSuccess(it1) }
                } else {
                    it.meta?.let { it1 -> view.onHomeFailed(it1.message) }
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