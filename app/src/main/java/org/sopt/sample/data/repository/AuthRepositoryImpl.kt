package org.sopt.sample.data.repository

import org.sopt.sample.data.entity.User
import org.sopt.sample.data.model.request.SignInRequest
import org.sopt.sample.data.model.request.SignUpRequest
import org.sopt.sample.data.model.response.SignInResponse
import org.sopt.sample.data.model.response.SignUpResponse
import org.sopt.sample.data.source.local.LocalPrefDataSource
import org.sopt.sample.data.source.remote.AuthDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localPrefDataSource: LocalPrefDataSource
) : AuthRepository {
    override fun setAutoLogin(isAutoLogin: Boolean) {
        localPrefDataSource.setAutoLogin(isAutoLogin)
    }

    override fun getAutoLogin(): Boolean = localPrefDataSource.getAutoLogin()

    override fun setUserInfo(user: User) {
        localPrefDataSource.setUserInfo(user)
    }

    override fun getUserInfo(): User = localPrefDataSource.getUserInfo()

    override suspend fun postSignIn(email: String, password: String): Result<SignInResponse> =
        runCatching { authDataSource.postSignIn(SignInRequest(email, password)) }

    override suspend fun postSignUp(
        email: String,
        password: String,
        name: String
    ): Result<SignUpResponse> =
        runCatching { authDataSource.postSignUp(SignUpRequest(email, password, name)) }
}
