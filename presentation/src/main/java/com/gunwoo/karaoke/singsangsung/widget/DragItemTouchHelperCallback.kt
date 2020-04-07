package com.gunwoo.karaoke.singsangsung.widget

import androidx.recyclerview.widget.ItemTouchHelper

import androidx.recyclerview.widget.RecyclerView
import com.gunwoo.karaoke.singsangsung.R
import kotlinx.android.synthetic.main.item_channel.view.*


class DragItemTouchHelperCallback(private val onItemCallbackListener: OnItemCallbackListener) : ItemTouchHelper.Callback() {

    interface OnItemCallbackListener {
        fun onMove(from: Int, to: Int)
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        (viewHolder.itemView).channel_root_view.setBackgroundColor(viewHolder.itemView.context.getColor(R.color.colorWhite))
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            (viewHolder!!.itemView).channel_root_view.setBackgroundColor(viewHolder.itemView.context.getColor(R.color.colorLightAccent))
        }
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        onItemCallbackListener.onMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) { }

}