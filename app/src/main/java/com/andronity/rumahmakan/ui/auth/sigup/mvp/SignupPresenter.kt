package com.andronity.rumahmakan.ui.auth.sigup.mvp

import android.net.Uri
import android.view.View
import com.andronity.rumahmakan.model.request.RegisterRequest
import com.andronity.rumahmakan.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignupPresenter(private val view: SignupContract.View) : SignupContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(registerRequest: RegisterRequest, view: View) {
        this.view.showLoading()
        val disposable = Api.getInstance().getApi()?.register(
            registerRequest.name,
            registerRequest.email,
            registerRequest.password,
            registerRequest.password_confirmation,
            registerRequest.address,
            registerRequest.city,
            registerRequest.houseNumber,
            registerRequest.phoneNumber
        )?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
            if (it.meta?.status.equals("success", true)) {
                it.data?.let { it1 -> this.view.onRegisterSuccess(it1, view) }
            } else {
                this.view.onRegisterFailed(it.meta?.message.toString())
            }

        }
        if (disposable != null) {
            mCompositeDisposable!!.add(disposable)
        }
    }

    override fun submitPhoto(filePath: Uri, view: View) {
        this.view.showLoading()
        val profileImageFile = filePath.path?.let { File(it) }
        val profileImageRequestBody =
            profileImageFile?.let { RequestBody.create("multipart/form-data".toMediaTypeOrNull(), it) }
        val profileImageParams = profileImageRequestBody?.let {
            MultipartBody.Part.createFormData(
                "file", profileImageFile.name, it
            )
        }

        val disposable = profileImageParams?.let { it ->
            Api.getInstance().getApi()?.registerPhoto(it)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { _ -> this.view.onRegisterPhotoSuccess(view) }
                    } else {
                        this.view.onRegisterFailed(it.meta?.message.toString())
                    }

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