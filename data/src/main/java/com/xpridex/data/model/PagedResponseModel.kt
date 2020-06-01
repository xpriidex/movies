package com.xpridex.data.model

import com.google.gson.annotations.SerializedName

data class PagedResponseModel<T>(
    @SerializedName("page") val page: Int = 0,
    @SerializedName("total_results") val totalResults: Int = 0,
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("results") val results: List<T> = ArrayList()
)






