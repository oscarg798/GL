package com.reyesmagos.gl.dashboard.network

import com.reyesmagos.gl.dashboard.entities.APIPost
import retrofit2.http.GET

interface GetPostService {

    @GET("feed")
    suspend fun getPosts():List<APIPost>

}
