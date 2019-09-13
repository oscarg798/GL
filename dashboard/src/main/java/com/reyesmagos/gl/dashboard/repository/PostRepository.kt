package com.reyesmagos.gl.dashboard.repository

import com.reyesmagos.gl.core.entities.Post

interface PostRepository {

    suspend fun getPosts(): List<Post>

}
