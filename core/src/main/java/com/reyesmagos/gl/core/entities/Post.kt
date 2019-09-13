package com.reyesmagos.gl.core.entities


data class Post(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val postBody: String,
    val date: String,
    val imageUrl: String?
)
