package com.example.naviassignment.features.closedPullRequests.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.naviassignment.features.closedPullRequests.domain.usecase.PullRequestUseCase
import java.lang.RuntimeException

class PullRequestViewModelFactory(
    private val useCase: PullRequestUseCase,
    private val viewCallback: ViewCallback
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PullRequestViewModel::class.java)) {
            return PullRequestViewModel(useCase, viewCallback) as T
        } else {
            throw RuntimeException("Unknown view model")
        }
    }
}