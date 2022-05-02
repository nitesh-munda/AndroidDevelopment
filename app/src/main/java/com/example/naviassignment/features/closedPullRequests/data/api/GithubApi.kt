package com.example.naviassignment.features.closedPullRequests.data.api

import com.example.naviassignment.features.closedPullRequests.data.api.model.GithubApiResponse

interface GithubApi {

    fun getClosedPullRequests() : GithubApiResponse
}