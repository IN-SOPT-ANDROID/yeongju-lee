package org.sopt.sample.data.repository

import org.sopt.sample.data.model.response.SignInResponse
import org.sopt.sample.data.model.response.SignUpResponse

interface AuthRepository {
//    fun isAutoLogin(): Boolean
//    fun initAutoLogin(isAutoLogin: Boolean)

    suspend fun postSignIn(
        email: String,
        password: String
    ): Result<SignInResponse>

    suspend fun postSignUp(
        email: String,
        password: String,
        name: String
    ): Result<SignUpResponse>
}
