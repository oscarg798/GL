package com.reyesmagos.gl.dashboard.repository

import com.reyesmagos.gl.core.entities.Post
import com.reyesmagos.gl.core.extensions.toPostDateFormat
import com.reyesmagos.gl.dashboard.network.GetPostService

class PostRepositoryImpl(private val getPostService: GetPostService) :
    PostRepository {

    override suspend fun getPosts(): List<Post> {
        return getPostService.getPosts().map {
            Post(
                it.id,
                it.firstName,
                it.lastName,
                it.postBody,
                it.creationDate.toPostDateFormat(),
                it.imageUrl
            )
        }
    }

}
