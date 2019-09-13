package com.reyesmagos.gl.dashboard.entities

import com.google.gson.annotations.SerializedName

data class APIPost(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("post_body")
    val postBody: String,
    @SerializedName("unix_timestamp")
    val creationDate: Long,
    @SerializedName("image")
    val imageUrl: String?
)
