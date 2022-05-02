package com.example.naviassignment.features.closedPullRequests.domain.usecase

interface UseCaseContract <Input, Output> {
    suspend fun execute(input: Input) : Output
}