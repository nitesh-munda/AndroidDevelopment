package com.example.naviassignment.features.closedPullRequests.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naviassignment.features.closedPullRequests.data.api.model.GithubApiRequest
import com.example.naviassignment.features.closedPullRequests.data.api.model.GithubApiResponse
import com.example.naviassignment.features.closedPullRequests.domain.repository.Error
import com.example.naviassignment.features.closedPullRequests.domain.repository.Success
import com.example.naviassignment.features.closedPullRequests.domain.usecase.PullRequestUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PullRequestViewModel(
    private val pullRequestUseCase: PullRequestUseCase,
    private val viewCallback: ViewCallback
) : ViewModel() {

    private val closedPullRequestLive = MutableLiveData<GithubApiResponse?>()
    val _closedPullRequestLive : LiveData<GithubApiResponse?> = closedPullRequestLive

    fun refreshPullRequest(githubApiResponse: GithubApiResponse?) {
        closedPullRequestLive.value = githubApiResponse
    }

    fun fetchClosedPullRequest(githubApiRequest: GithubApiRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                viewCallback.showLoading()
            }
            when(pullRequestUseCase.execute(githubApiRequest)) {
                is Error -> {
                    withContext(Dispatchers.Main) {
                        viewCallback.hideLoading()
                        viewCallback.hideData()
                        viewCallback.showErrorScreen()
                    }
                }
                is Success<*> -> {
                    withContext(Dispatchers.Main) {
                        viewCallback.hideLoading()
                        viewCallback.hideErrorScreen()
                        viewCallback.showData()
                    }
                }
            }
        }
    }

}