package com.andronity.rumahmakan.network

import com.andronity.rumahmakan.model.response.Wrapper
import com.andronity.rumahmakan.model.response.checkout.ResponseCheckout
import com.andronity.rumahmakan.model.response.home.ResponseFood
import com.andronity.rumahmakan.model.response.login.ResponseLogin
import com.andronity.rumahmakan.model.response.transaction.ResponseTransaction
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Endpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String, @Field("password") password: String
    ): Observable<Wrapper<ResponseLogin>>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
        @Field("address") address: String,
        @Field("city") city: String,
        @Field("houseNumber") houseNumber: String,
        @Field("phoneNumber") phoneNumber: String
    ): Observable<Wrapper<ResponseLogin>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(@Part profileImage: MultipartBody.Part): Observable<Wrapper<Any>>

    @GET("food")
    fun getFood(): Observable<Wrapper<ResponseFood>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
        @Field("food_id") food_id: String,
        @Field("user_id") user_id: String,
        @Field("quantity") quantity: String,
        @Field("total") total: String,
        @Field("status") status: String
    ): Observable<Wrapper<ResponseCheckout>>

    @GET("transaction")
    fun transaction(): Observable<Wrapper<ResponseTransaction>>

}