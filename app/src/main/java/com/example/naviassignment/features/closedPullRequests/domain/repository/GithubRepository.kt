package com.example.naviassignment.features.closedPullRequests.domain.repository

import com.example.naviassignment.features.closedPullRequests.data.api.GithubApi
import com.example.naviassignment.features.closedPullRequests.data.api.model.PullRequestData
import com.example.naviassignment.features.closedPullRequests.data.api.model.RepoDetails
import io.reactivex.Single

class GithubRepository(
    private val githubApi: GithubApi,
    private val repoDetails: RepoDetails
) {
    fun getAllClosedPullRequest(): Single<List<PullRequestData>> {
        return githubApi.getClosedPullRequests(
            username = repoDetails.username,
            repo = repoDetails.repo
        )
    }
}