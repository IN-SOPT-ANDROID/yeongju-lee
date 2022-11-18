package org.sopt.sample.data.model.response

import kotlinx.serialization.Serializable
import org.sopt.sample.data.entity.User

@Serializable
data class SignInResponse(
    val status: Int,
    val message: String,
    val result: User
)
