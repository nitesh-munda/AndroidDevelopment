package com.example.naviassignment.features.closedPullRequests.domain.repository

import com.example.naviassignment.features.closedPullRequests.data.api.GithubApi
import com.example.naviassignment.features.closedPullRequests.data.api.model.GithubApiResponse
import retrofit2.Response

class GithubRepository(
    private val githubApi: GithubApi
) {
    suspend fun getAllClosedPullRequest(): ResponseResult {
        val result = githubApi.getClosedPullRequests()
        val data = result.body()
        if (result.isSuccessful && data != null) {
            return Success(data)
        } else {
            return Error(getHttpError(result))
        }
    }

    private fun getHttpError(result: Response<GithubApiResponse>) =
        result.message() ?: "Something went wrong"
}