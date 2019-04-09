package com.interco.e.daggerrxretrofit.ui.splash

import com.comuto.authent.data.model.ApiToken
import com.interco.e.daggerrxretrofit.base.activity.BaseActivityViewInterface

/**
 * Created by emine on 04/01/2019.
 */
interface SplashViewInterface : BaseActivityViewInterface {

    fun showMessageError(message: String)
    fun navigateTohome()

}
