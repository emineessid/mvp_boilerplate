package com.interco.e.daggerrxretrofit.base.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.interco.e.daggerrxretrofit.base.BaseRecyclerListener

abstract class BaseViewHolder<ITEM, LISTENER : BaseRecyclerListener<*>>(itemView: View, index: Int)//ADD FIND VIEWS HERE !!!!
    : RecyclerView.ViewHolder(itemView) {

    /**
     * Bind data to the item and set listener if needed.
     *
     * @param item     object, associated with the item.
     * @param listener listener a listener [BaseRecyclerListener] which has to b set at the item (if not `null`).
     */
    abstract fun onBind(item: ITEM, listener: LISTENER?, index: Int)
}