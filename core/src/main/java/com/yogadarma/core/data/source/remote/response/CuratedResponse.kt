package com.yogadarma.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CuratedResponse(

    @field:SerializedName("next_page")
    val nextPage: String? = null,

    @field:SerializedName("per_page")
    val perPage: Int? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("photos")
    val photos: List<PhotoItem?>? = null
)
