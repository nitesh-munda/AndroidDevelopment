package com.example.naviassignment.features.closedPullRequests.domain.repository

import com.example.naviassignment.features.closedPullRequests.data.api.model.PullRequestData

sealed class ResponseResult

data class Error(val error: String): ResponseResult()
data class Success<T>(val response: List<PullRequestData>): ResponseResult()