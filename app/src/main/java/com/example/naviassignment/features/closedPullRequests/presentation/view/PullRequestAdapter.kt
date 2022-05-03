package com.example.naviassignment.features.closedPullRequests.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naviassignment.R
import com.example.naviassignment.features.closedPullRequests.data.api.model.PullRequestData

class PullRequestAdapter(
    private var itemList: List<PullRequestData>
) : RecyclerView.Adapter<PullRequestViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(newItemList: List<PullRequestData>) {
        itemList = newItemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.rv_pull_request_item, parent, false)
        return PullRequestViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

}