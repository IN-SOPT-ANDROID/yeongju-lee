package org.sopt.sample.data.repository

import org.sopt.sample.data.entity.response.FollowerResponse

interface ReqResRepository {
    suspend fun getFollower(): Result<FollowerResponse>
}
