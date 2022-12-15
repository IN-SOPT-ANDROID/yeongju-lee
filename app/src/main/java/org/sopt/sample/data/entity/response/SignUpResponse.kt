package org.sopt.sample.data.entity.response

import kotlinx.serialization.Serializable
import org.sopt.sample.data.entity.User

@Serializable
data class SignUpResponse(
    val status: Int,
    val message: String,
    val newUser: User
)
