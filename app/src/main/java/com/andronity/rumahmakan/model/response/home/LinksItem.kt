package com.andronity.rumahmakan.model.response.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LinksItem(

    @Expose @field:SerializedName("active") val active: Boolean,

    @Expose @field:SerializedName("label") val label: String,

    @Expose @field:SerializedName("url") val url: Any
)