package com.example.naviassignment.features.closedPullRequests.domain.usecase

import com.example.naviassignment.features.closedPullRequests.data.api.model.PullRequestData
import com.example.naviassignment.features.closedPullRequests.domain.repository.GithubRepository
import io.reactivex.Single

class PullRequestUseCase(
    private val githubRepository: GithubRepository
) : UseCaseContract<Unit, Single<List<PullRequestData>>> {
    override fun execute(input: Unit): Single<List<PullRequestData>> {
        return githubRepository.getAllClosedPullRequest()
    }
}