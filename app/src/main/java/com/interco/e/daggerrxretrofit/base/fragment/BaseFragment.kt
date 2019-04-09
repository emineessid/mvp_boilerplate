package com.interco.e.daggerrxretrofit.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.View

import com.interco.e.daggerrxretrofit.base.activity.BaseActivity
import com.interco.e.daggerrxretrofit.base.activity.BaseActivityViewInterface
import com.interco.e.daggerrxretrofit.base.presenter.BaseActivityPresenter
import com.interco.e.daggerrxretrofit.base.repository.BaseRepository

/**
 * Created by E²
 */

abstract class BaseFragment<P : BaseActivityPresenter<*, *>, O : BaseActivityViewInterface> : SuperBaseFragment<P, O>() {

    protected lateinit var activity: BaseActivity<*, *>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = (context as BaseActivity<*, *>?)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    fun toggleLoading(show: Boolean) {
        if (isAdded)
            activity.toggleLoading(show)
    }


}