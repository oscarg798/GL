package com.reyesmagos.gl.dashboard

import com.reyesmagos.gl.core.entities.Post
import com.reyesmagos.gl.dashboard.entities.APIPost
import com.reyesmagos.gl.dashboard.network.GetPostService
import com.reyesmagos.gl.dashboard.repository.PostRepository
import com.reyesmagos.gl.dashboard.repository.PostRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class PostRepositoryTest {

    @MockK
    lateinit var service: GetPostService

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        coEvery {
            service.getPosts()
        }.answers {
            listOf(APIPost(1, "2", "3", "4", 1525940534.toLong(), null))
        }
    }

    @Test
    fun shouldGetPost() {
        //Given
        val repository: PostRepository =
            PostRepositoryImpl(service)

        //When
        val result: List<Post> = runBlocking {
            repository.getPosts()
        }

        //Then
        result.size shouldEqual 1
        result[0].id shouldEqual 1
        result[0].firstName shouldEqual "2"
        result[0].lastName shouldEqual "3"
        result[0].postBody shouldEqual "4"
        result[0].date shouldEqual "10/05/2018"

    }
}
