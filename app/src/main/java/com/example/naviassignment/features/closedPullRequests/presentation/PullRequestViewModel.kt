package com.example.naviassignment.features.closedPullRequests.presentation

import androidx.lifecycle.ViewModel
import com.example.naviassignment.features.closedPullRequests.domain.usecase.PullRequestUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PullRequestViewModel(
    private val pullRequestUseCase: PullRequestUseCase,
    private val viewCallback: ViewCallback
) : ViewModel() {
    fun fetchClosedPullRequest() {
        loadingState()
        pullRequestUseCase
            .execute(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                viewCallback.hideLoading()
                viewCallback.hideData()
                viewCallback.showErrorScreen(it)
            }
            .subscribeBy {
                if (it.isNullOrEmpty().not()) {
                    viewCallback.hideLoading()
                    viewCallback.hideErrorScreen()
                    viewCallback.showData(it)
                } else {
                    viewCallback.hideLoading()
                    viewCallback.hideData()
                    viewCallback.showErrorScreen(Throwable("No closed pull request found"))
                }
            }
    }

    private fun loadingState() {
        viewCallback.showLoading()
        viewCallback.hideData()
        viewCallback.hideErrorScreen()
    }

}