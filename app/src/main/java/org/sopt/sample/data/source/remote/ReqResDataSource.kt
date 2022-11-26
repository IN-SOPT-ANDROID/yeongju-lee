package org.sopt.sample.data.source.remote

import org.sopt.sample.data.model.response.FollowerResponse
import org.sopt.sample.data.service.ReqResService
import javax.inject.Inject

class ReqResDataSource @Inject constructor(
    private val reqResService: ReqResService
) {
    suspend fun getFollower(): FollowerResponse =
        reqResService.getFollowers()
}
