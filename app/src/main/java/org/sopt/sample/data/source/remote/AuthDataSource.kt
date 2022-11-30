package org.sopt.sample.data.source.remote

import org.sopt.sample.data.entity.request.SignInRequest
import org.sopt.sample.data.entity.request.SignUpRequest
import org.sopt.sample.data.entity.response.SignInResponse
import org.sopt.sample.data.entity.response.SignUpResponse
import org.sopt.sample.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun postSignIn(signInRequest: SignInRequest): SignInResponse =
        authService.postSignIn(signInRequest)

    suspend fun postSignUp(signUpRequest: SignUpRequest): SignUpResponse =
        authService.postSignUp(signUpRequest)
}
