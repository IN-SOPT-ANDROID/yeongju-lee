package org.sopt.sample.data.repository

import org.sopt.sample.data.entity.response.FollowerResponse
import org.sopt.sample.data.source.remote.ReqResDataSource
import javax.inject.Inject

class ReqResRepositoryImpl @Inject constructor(
    private val reqResDataSource: ReqResDataSource
) : ReqResRepository {
    override suspend fun getFollower(): Result<FollowerResponse> =
        runCatching { reqResDataSource.getFollower() }
}
