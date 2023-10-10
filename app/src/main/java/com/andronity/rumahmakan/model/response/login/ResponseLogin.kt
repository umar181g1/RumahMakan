package com.andronity.rumahmakan.model.response.login

import com.google.gson.annotations.SerializedName

 class ResponseLogin(

	@field:SerializedName("access_token")
	val accessToken: String,

	@field:SerializedName("token_type")
	val tokenType: String,

	@field:SerializedName("user")
	val user: User
)