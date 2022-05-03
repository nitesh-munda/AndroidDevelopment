package com.example.naviassignment.features.closedPullRequests.presentation

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.naviassignment.R
import com.example.naviassignment.features.closedPullRequests.data.api.model.PullRequestData
import com.example.naviassignment.features.closedPullRequests.presentation.view.PullRequestAdapter
import com.example.naviassignment.util.setVisibility

class ViewCallbackImpl(
    private val view: View,
    private val adapter: PullRequestAdapter
) : ViewCallback {

    private val rvList: RecyclerView = view.findViewById<RecyclerView>(R.id.rvContent)
    private val errorContent: View = view.findViewById<View>(R.id.error)
    private val loader: ProgressBar =
        view.findViewById<ProgressBar>(R.id.loader)
    private val errorText: AppCompatTextView =
        errorContent.findViewById<AppCompatTextView>(R.id.tvError)
    private val errorButton: AppCompatButton =
        errorContent.findViewById<AppCompatButton>(R.id.btCta)

    override fun showErrorScreen(throwable: Throwable) {
        errorContent.setVisibility(true)
        errorText.text = throwable.message
            ?: view.context.getString(R.string.generic_error_text)
    }

    override fun hideErrorScreen() {
        errorContent.setVisibility(false)
    }

    override fun showData(newItemList: List<PullRequestData>?) {
        rvList.setVisibility(true)
        adapter.refreshList(newItemList ?: emptyList())
    }

    override fun hideData() {
        rvList.setVisibility(false)
    }

    override fun showLoading() {
        loader.setVisibility(true)
    }

    override fun hideLoading() {
        loader.setVisibility(false)
    }

}