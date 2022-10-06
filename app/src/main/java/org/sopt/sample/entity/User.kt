package org.sopt.sample.entity

import java.io.Serializable

data class User(
    var id: String? = "",
    var pwd: String? = "",
    var mbti: String? = ""
) : Serializable
