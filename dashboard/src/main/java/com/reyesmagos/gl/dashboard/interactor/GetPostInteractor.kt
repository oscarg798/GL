package com.reyesmagos.gl.dashboard.interactor

import com.reyesmagos.gl.core.Interactor
import com.reyesmagos.gl.core.entities.Post
import com.reyesmagos.gl.dashboard.repository.PostRepository

class GetPostInteractor(private val postRepository: PostRepository) : Interactor<List<Post>, Unit> {

    override suspend fun invoke(params: Unit): List<Post> {
        return postRepository.getPosts()
    }

}
