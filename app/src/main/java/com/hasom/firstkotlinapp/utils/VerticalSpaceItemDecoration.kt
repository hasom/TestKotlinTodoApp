package com.hasom.firstkotlinapp.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        // 마지막 아이템이 아닌 경우, 공백 추가
        if (parent.getChildAdapterPosition(view) != parent.adapter.itemCount - 1) {
            outRect.bottom = verticalSpaceHeight
        }
    }
}