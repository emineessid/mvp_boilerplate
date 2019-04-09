package com.interco.e.daggerrxretrofit.base.activity

import androidx.annotation.Keep

import com.interco.e.daggerrxretrofit.base.BaseViewInterface

@Keep
interface BaseActivityViewInterface : BaseViewInterface {
    fun toggleLoading(yesNo: Boolean)

}