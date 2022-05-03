package com.example.naviassignment.features.closedPullRequests.presentation.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val isLastPosition = view.let {
            parent.adapter?.itemCount == parent.getChildAdapterPosition(view)
        }
        outRect.bottom = if (isLastPosition) 0 else 10
    }
}