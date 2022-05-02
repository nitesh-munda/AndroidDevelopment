package com.example.naviassignment.features.closedPullRequests.presentation

interface ViewCallback {
    fun showErrorScreen()
    fun hideErrorScreen()
    fun showData()
    fun hideData()
    fun showLoading()
    fun hideLoading()
}