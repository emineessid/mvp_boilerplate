package com.interco.e.daggerrxretrofit.base.fragment

import androidx.annotation.Keep
import com.interco.e.daggerrxretrofit.base.activity.BaseActivityViewInterface

import com.interco.e.daggerrxretrofit.base.presenter.BaseActivityPresenter
import com.interco.e.daggerrxretrofit.base.presenter.BasePresenterFragment
import com.interco.e.daggerrxretrofit.base.repository.BaseRepository

@Keep
abstract class SuperBaseFragment<P : BaseActivityPresenter<*,*>, O : BaseActivityViewInterface> : BasePresenterFragment<P, O>() {

    val isFragmentRunning: Boolean
        get() = this.isAdded && !this.isDetached && !this.isHidden && !this.isRemoving && !this.activity!!.isFinishing

    abstract fun canGoBack(): Boolean
}
