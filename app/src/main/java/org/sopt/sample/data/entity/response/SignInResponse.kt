package org.sopt.sample.data.entity.response

import kotlinx.serialization.Serializable
import org.sopt.sample.data.entity.User

@Serializable
data class SignInResponse(
    val status: Int,
    val message: String,
    val result: User
)
