package org.sopt.sample.data.service

import org.sopt.sample.data.entity.request.SignInRequest
import org.sopt.sample.data.entity.request.SignUpRequest
import org.sopt.sample.data.entity.response.SignInResponse
import org.sopt.sample.data.entity.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/signin")
    suspend fun postSignIn(@Body body: SignInRequest): SignInResponse

    @POST("api/user/signup")
    suspend fun postSignUp(@Body body: SignUpRequest): SignUpResponse
}
