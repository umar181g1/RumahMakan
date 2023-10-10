package com.andronity.rumahmakan.model.response.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(

    @Expose @field:SerializedName("profile_photo_url") val profilePhotoUrl: String,

    @Expose @field:SerializedName("address") val address: String,

    @Expose @field:SerializedName("city") val city: String,

    @Expose @field:SerializedName("roles") val roles: String,

    @Expose @field:SerializedName("houseNumber") val houseNumber: String,

    @Expose @field:SerializedName("created_at") val createdAt: Long,

    @Expose @field:SerializedName("email_verified_at") val emailVerifiedAt: Any,

    @Expose @field:SerializedName("current_team_id") val currentTeamId: Any,

    @Expose @field:SerializedName("phoneNumber") val phoneNumber: String,

    @Expose @field:SerializedName("updated_at") val updatedAt: Long,

    @Expose @field:SerializedName("name") val name: String,

    @Expose @field:SerializedName("id") val id: Int,

    @Expose @field:SerializedName("profile_photo_path") val profilePhotoPath: Any,

    @Expose @field:SerializedName("two_factor_confirmed_at") val twoFactorConfirmedAt: Any,

    @Expose @field:SerializedName("email") val email: String
)