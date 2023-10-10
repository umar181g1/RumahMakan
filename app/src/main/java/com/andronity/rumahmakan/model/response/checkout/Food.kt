package com.andronity.rumahmakan.model.response.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Food(
    @Expose @field:SerializedName("picturePath") val picturePath: String,

    @Expose @field:SerializedName("types") val types: String,

    @Expose @field:SerializedName("updated_at") val updatedAt: Long,

    @Expose @field:SerializedName("rate") val rate: Int,

    @Expose @field:SerializedName("price") val price: Int,

    @Expose @field:SerializedName("name") val name: String,

    @Expose @field:SerializedName("description") val description: String,

    @Expose @field:SerializedName("ingredients") val ingredients: String,

    @Expose @field:SerializedName("created_at") val createdAt: Long,

    @Expose @field:SerializedName("id") val id: Int,

    @Expose @field:SerializedName("deleted_at") val deletedAt: Any
)