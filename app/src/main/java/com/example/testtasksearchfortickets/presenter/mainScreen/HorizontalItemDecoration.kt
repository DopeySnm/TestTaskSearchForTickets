package com.example.testtasksearchfortickets.presenter.mainScreen

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        rect: Rect,
        view: View,
        parent: RecyclerView,
        s: RecyclerView.State
    ) {
        parent.adapter?.let { adapter ->
            rect.right = when (parent.getChildAdapterPosition(view)) {
                RecyclerView.NO_POSITION,
                adapter.itemCount - 1 -> 0
                else -> spacing
            }
        }
    }

}
