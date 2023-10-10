package com.andronity.rumahmakan.model.response.login

import com.google.gson.annotations.SerializedName

 class User(

	@field:SerializedName("profile_photo_url")
	val profilePhotoUrl: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("roles")
	val roles: String,

	@field:SerializedName("houseNumber")
	val houseNumber: String,

	@field:SerializedName("created_at")
	val createdAt: Long,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any,

	@field:SerializedName("current_team_id")
	val currentTeamId: Any,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String,

	@field:SerializedName("updated_at")
	val updatedAt: Long,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("profile_photo_path")
	val profilePhotoPath: Any,

	@field:SerializedName("two_factor_confirmed_at")
	val twoFactorConfirmedAt: Any,

	@field:SerializedName("email")
	val email: String
)