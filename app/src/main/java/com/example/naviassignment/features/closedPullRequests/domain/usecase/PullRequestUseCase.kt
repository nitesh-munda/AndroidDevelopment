package com.example.naviassignment.features.closedPullRequests.domain.usecase

import com.example.naviassignment.features.closedPullRequests.data.api.model.GithubApiRequest
import com.example.naviassignment.features.closedPullRequests.domain.repository.GithubRepository
import com.example.naviassignment.features.closedPullRequests.domain.repository.ResponseResult

class PullRequestUseCase(private val githubRepository: GithubRepository) : UseCaseContract<GithubApiRequest, ResponseResult> {
    override suspend fun execute(input: GithubApiRequest): ResponseResult {
        return githubRepository.getAllClosedPullRequest()
    }
}