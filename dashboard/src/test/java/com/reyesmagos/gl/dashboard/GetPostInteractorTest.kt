package com.reyesmagos.gl.dashboard

import com.reyesmagos.gl.core.Interactor
import com.reyesmagos.gl.core.entities.Post
import com.reyesmagos.gl.dashboard.interactor.GetPostInteractor
import com.reyesmagos.gl.dashboard.repository.PostRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class GetPostInteractorTest   {

    @MockK
    lateinit var repository: PostRepository


    @Before
    fun setup() {
        MockKAnnotations.init(this)

        coEvery {
            repository.getPosts()
        }.answers {
            listOf(Post(1, "2", "3", "4", "10/05/2018", null))
        }
    }

    @Test
    fun shouldGetPost(){
        //Given
        val interactor: Interactor<List<Post>, Unit> =
            GetPostInteractor(repository)

        //When
        val result = runBlocking {
            interactor(Unit)
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
