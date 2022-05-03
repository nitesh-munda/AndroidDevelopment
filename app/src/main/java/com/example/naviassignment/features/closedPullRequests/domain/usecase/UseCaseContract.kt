package com.example.naviassignment.features.closedPullRequests.domain.usecase

interface UseCaseContract <Input, Output> {
    fun execute(input: Input) : Output
}