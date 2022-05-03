package com.example.naviassignment.features.closedPullRequests.presentation.view

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.naviassignment.R
import com.example.naviassignment.features.closedPullRequests.data.api.model.*
import com.example.naviassignment.util.getSpannable
import com.example.naviassignment.util.setVisibility

class PullRequestViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val ivAvatar = view.findViewById<AppCompatImageView>(R.id.ivAvatar)
    private val tvTitle = view.findViewById<AppCompatTextView>(R.id.tvTitle)
    private val tvUserName = view.findViewById<AppCompatTextView>(R.id.tvUserName)
    private val tvCreatedDate = view.findViewById<AppCompatTextView>(R.id.tvCreatedDate)
    private val tvClosedDate = view.findViewById<AppCompatTextView>(R.id.tvClosedDate)
    private val tvStatus = view.findViewById<AppCompatTextView>(R.id.tvStatus)

    fun bind(response: PullRequestData) {
        with(response) {
            setIcon(this.userDetails.avatarUrl)
            setTitle(this.title)
            setUserName(this.userDetails.login)
            setDates(this.createdAt, this.closedAt)
            setStatusTag(this.getState())
        }
    }

    private fun setStatusTag(state: StateOfPR) {
        tvStatus.text = state.data
        when(state) {
            is Closed -> {
                tvStatus.setBackgroundColor(Color.parseColor("#FF0000"))
            }
            is Merged -> {
                tvStatus.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            }
            is Open -> {
                tvStatus.setBackgroundColor(Color.parseColor("#00FF00"))
            }
        }
    }

    private fun setDates(createdAt: String, closedAt: String) {
        tvCreatedDate.text = createdAt.getSpannable("Created at:")
        tvClosedDate.text =  closedAt.getSpannable("Closed at:")
    }

    private fun setUserName(login: String) {
        tvUserName.text = login.getSpannable("Username: ")
    }

    private fun setTitle(title: String) {
        tvTitle.text = title.getSpannable("Title: ")
    }

    private fun setIcon(avatarUrl: String?) {
        try {
            ivAvatar.setVisibility(true)
            Glide.with(view.context)
                .load(avatarUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(ivAvatar)

        } catch (e: Exception) {
            Log.e("PullRequestViewHolder", "Caught exception")
            ivAvatar.setVisibility(false)
        }
    }
}
