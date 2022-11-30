package org.sopt.sample.data.repository

import org.sopt.sample.data.entity.User
import org.sopt.sample.data.model.response.SignInResponse
import org.sopt.sample.data.model.response.SignUpResponse

interface AuthRepository {
    fun setAutoLogin(isAutoLogin: Boolean)
    fun getAutoLogin(): Boolean

    fun setUserInfo(user: User)
    fun getUserInfo(): User

    fun setLogout()

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
