package org.sopt.sample.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.sample.data.entity.Follower

@Serializable
data class FollowerResponse(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val data: List<Follower>,
    @SerialName("support")
    val support: Support
) {
    @Serializable
    data class Support(
        val url: String,
        val text: String
    )
}
