package com.example.naviassignment.features.closedPullRequests.domain.repository

sealed class ResponseResult

data class Error(val error: String): ResponseResult()
data class Success<T>(val response: T): ResponseResult()