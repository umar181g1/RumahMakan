package com.andronity.rumahmakan.model.response.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseCheckout(

    @Expose @field:SerializedName("total") val total: Int,

    @Expose @field:SerializedName("payment_url") val paymentUrl: String,

    @Expose @field:SerializedName("quantity") val quantity: Int,

    @Expose @field:SerializedName("updated_at") val updatedAt: Long,

    @Expose @field:SerializedName("user_id") val userId: Int,

    @Expose @field:SerializedName("created_at") val createdAt: Long,

    @Expose @field:SerializedName("id") val id: Int,

    @Expose @field:SerializedName("food_id") val foodId: Int,

    @Expose @field:SerializedName("deleted_at") val deletedAt: Any,

    @Expose @field:SerializedName("user") val user: User,

    @Expose @field:SerializedName("food") val food: Food,

    @Expose @field:SerializedName("status") val status: String
)