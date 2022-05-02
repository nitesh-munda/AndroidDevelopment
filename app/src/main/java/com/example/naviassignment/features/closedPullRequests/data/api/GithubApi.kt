package com.example.naviassignment.features.closedPullRequests.data.api

import com.example.naviassignment.features.closedPullRequests.data.api.model.GithubApiResponse
import com.example.naviassignment.features.closedPullRequests.data.apiConstants.APIEndpoints.PULL_REQUEST
import retrofit2.http.GET

interface GithubApi {
    @GET(PULL_REQUEST)
    fun getClosedPullRequests() : GithubApiResponse
}