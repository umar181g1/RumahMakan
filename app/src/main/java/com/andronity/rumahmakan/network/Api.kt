package com.andronity.rumahmakan.network

import android.util.Log
import com.andronity.rumahmakan.BuildConfig
import com.andronity.rumahmakan.BuildConfig.BASE_URL
import com.andronity.rumahmakan.RumahMakan
import com.andronity.rumahmakan.utils.Helpers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Api {

    private lateinit var api: Retrofit
    private var endpoint: Endpoint? = null

    companion object {
        private val mInstance: Api = Api()

        @Synchronized
        fun getInstance(): Api {
            return mInstance
        }
    }

    init {
        buildRetrofit()
    }

    fun getApi(): Endpoint? {
        if (endpoint == null) {
            endpoint = api.create(Endpoint::class.java)
        }
        return endpoint
    }

    private fun buildRetrofit() {
        val token = RumahMakan.getApp().getToken()
        buildRetrofitClient(token)
    }

    fun buildRetrofitClient(token: String?) {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)

        }

        builder.addInterceptor(getIntercerptorheader("Authorization", "Bearer $token"))

        val okHttpClient = builder.build()
        api = Retrofit.Builder().baseUrl(BASE_URL + "api/").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Helpers.getDefaultGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

        this.endpoint


    }

    private fun getIntercerptorheader(headerName: String, headerValue: String): Interceptor {
        val header = HashMap<String, String>()
        header.put(headerName, headerValue)
        return getIntercerptorheader(header)
    }

    private fun getIntercerptorheader(headers: Map<String, String>): Interceptor {
        return Interceptor {
            val original = it.request()
            val builder = original.newBuilder()
            for ((key, value) in headers) {
                builder.addHeader(key, value)
            }
            builder.method(original.method, original.body)
            it.proceed(builder.build())
        }
    }
}