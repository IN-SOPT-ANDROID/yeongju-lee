package org.sopt.sample.data.service

import org.sopt.sample.data.model.response.FollowerResponse
import retrofit2.http.GET

interface ReqResService {
    @GET("api/users?page=2")
    suspend fun getFollowers(): FollowerResponse
}
