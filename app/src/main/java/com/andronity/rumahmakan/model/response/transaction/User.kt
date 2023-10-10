package com.andronity.rumahmakan.model.response.transaction

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class User(

    @Expose @field:SerializedName("profile_photo_url") val profilePhotoUrl: String,

    @Expose @field:SerializedName("address") val address: String,

    @Expose @field:SerializedName("city") val city: String,

    @Expose @field:SerializedName("roles") val roles: String,

    @Expose @field:SerializedName("houseNumber") val houseNumber: String,

    @Expose @field:SerializedName("created_at") val createdAt: Long,

    @Expose @field:SerializedName("email_verified_at") val emailVerifiedAt: String,

    @Expose @field:SerializedName("current_team_id") val currentTeamId: String,

    @Expose @field:SerializedName("phoneNumber") val phoneNumber: String,

    @Expose @field:SerializedName("updated_at") val updatedAt: Long,

    @Expose @field:SerializedName("name") val name: String,

    @Expose @field:SerializedName("id") val id: Int,

    @Expose @field:SerializedName("profile_photo_path") val profilePhotoPath: String,

    @Expose @field:SerializedName("email") val email: String
):Parcelable