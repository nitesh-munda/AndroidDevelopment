package com.example.naviassignment.features.closedPullRequests.data.api.model

import com.google.gson.annotations.SerializedName

data class PullRequestData(
    @SerializedName("id")
    val id: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val userDetails: UserDetails,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("closed_at")
    val closedAt: String
) {
    fun getState() : StateOfPR {
        return when(state) {
            "open" -> Open("Open")
            "closed" -> Closed("Closed")
            "merged" -> Merged("Merged")
            else -> Open("Open")
        }
    }
}

data class UserDetails(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)

sealed class StateOfPR {
    abstract val data: String
}
data class Open(override val data: String): StateOfPR()
data class Closed(override val data: String): StateOfPR()
data class Merged(override val data: String): StateOfPR()
