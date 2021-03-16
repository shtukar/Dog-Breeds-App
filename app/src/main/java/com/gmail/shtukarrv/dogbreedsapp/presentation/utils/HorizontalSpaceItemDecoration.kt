package com.gmail.shtukarrv.dogbreedsapp.presentation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val count = parent.adapter?.itemCount ?: 0

        if (parent.getChildAdapterPosition(view) != count - 1) {
            outRect.bottom = space
        }
    }

}