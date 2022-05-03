package com.example.naviassignment.features.closedPullRequests.presentation

import com.example.naviassignment.features.closedPullRequests.data.api.model.PullRequestData


interface ViewCallback {
    fun showErrorScreen(throwable: Throwable)
    fun hideErrorScreen()
    fun showData(newItemList: List<PullRequestData>?)
    fun hideData()
    fun showLoading()
    fun hideLoading()
}