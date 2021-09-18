package com.yogadarma.awesomeapp.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginGridItemDecoration(
    private val spaceSize: Int,
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = spaceSize
            bottom = spaceSize

            when ((parent.getChildAdapterPosition(view) + 1) % spanCount) {
                0 -> left = spaceSize
                1 -> right = spaceSize

            }
        }
    }
}