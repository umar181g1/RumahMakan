package com.andronity.rumahmakan.ui.auth.sigin

import com.andronity.rumahmakan.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SiginPresenter(private val view: SignContract.View) : SignContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun subimtLogin(email: String, password: String) {
        view.showLoading()
        val disposable =
            Api.getInstance().getApi()?.login(email, password)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onLoginSuccess(it1) }
                    } else {
                        it.meta?.let { it1 -> view.onLoginFailed(it1.message) }
                    }

                }
        if (disposable != null) {
            mCompositeDisposable!!.add(disposable)
        }

    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
        mCompositeDisposable?.clear()
    }
}


