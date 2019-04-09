package com.interco.e.daggerrxretrofit.base

import android.view.View

interface BaseRecyclerListener<TYPE> {
    fun onItemClickWithPosition(item: TYPE, position: Int, view: View)

    fun onItemClick(item: TYPE, view: View)


}