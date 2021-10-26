package com.example.application.presentation.main.impl

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R

class ImageDecorator(
    val context: Context,
    val columns: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val padding = context.resources.getDimensionPixelSize(R.dimen.image_margin)

        outRect.set(
            calculateLeft(position, padding),
            calculateTop(position, padding),
            calculateRight(position, padding),
            calculateBottom(padding)
        )
    }

    private fun calculateLeft(position: Int, padding: Int) =
        padding

    private fun calculateTop(position: Int, padding: Int) =
        if(position < columns) padding else 0

    private fun calculateRight(position: Int, padding: Int) =
        if(position % columns == columns - 1) padding else 0

    private fun calculateBottom(padding: Int) =
        padding
}