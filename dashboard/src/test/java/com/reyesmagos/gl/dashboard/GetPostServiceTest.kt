package com.reyesmagos.gl.dashboard

import com.reyesmagos.gl.core.di.NetworkModule
import com.reyesmagos.gl.dashboard.network.GetPostService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.amshove.kluent.shouldEqual
import org.junit.Test

import org.junit.Before


class GetPostServiceTest: MockServerTest() {

    private val networkModule = NetworkModule()

    @Before
    override fun setup() {
        super.setup()
        mockServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"first_name\": \"Averill\",\n" +
                        "        \"last_name\": \"Erricker\",\n" +
                        "        \"post_body\": \"Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.\",\n" +
                        "        \"unix_timestamp\": \"1525940534\"\n" +
                        "    }" +
                        "]"))
    }

    @Test
    fun shouldGetAPost() {
        //Given
        val service = networkModule.provideRetrofit(
            mockServer.url(" ").toString(),
            networkModule.provideGsonConverter(),
            networkModule.provideHttpClient(networkModule.provideLogginInterceptor())
        ).create(GetPostService::class.java)

        val posts = runBlocking {
            service.getPosts()
        }

        posts.size shouldEqual  1
        posts[0].id shouldEqual 1
        posts[0].firstName shouldEqual "Averill"
        posts[0].lastName shouldEqual "Erricker"
        posts[0].postBody shouldEqual "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl."
        posts[0].creationDate shouldEqual 1525940534.toLong()

    }
}
