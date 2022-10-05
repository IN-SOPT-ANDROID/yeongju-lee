package org.sopt.sample.entity

import java.io.Serializable

data class User(
    val id: String? = "",
    val pwd: String? = "",
    val mbti: String? = ""
) : Serializable
