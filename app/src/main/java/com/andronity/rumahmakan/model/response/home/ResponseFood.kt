package com.andronity.rumahmakan.model.response.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseFood(

    @Expose @field:SerializedName("per_page") val perPage: Int,

    @Expose @field:SerializedName("data") val data: List<DataItem>,

    @Expose @field:SerializedName("last_page") val lastPage: Int,

    @Expose @field:SerializedName("next_page_url") val nextPageUrl: Any,

    @Expose @field:SerializedName("prev_page_url") val prevPageUrl: Any,

    @Expose @field:SerializedName("first_page_url") val firstPageUrl: String,

    @Expose @field:SerializedName("path") val path: String,

    @Expose @field:SerializedName("total") val total: Int,

    @Expose @field:SerializedName("last_page_url") val lastPageUrl: String,

    @Expose @field:SerializedName("from") val from: Int,

    @Expose @field:SerializedName("links") val links: List<LinksItem>,

    @Expose @field:SerializedName("to") val to: Int,

    @Expose @field:SerializedName("current_page") val currentPage: Int
)