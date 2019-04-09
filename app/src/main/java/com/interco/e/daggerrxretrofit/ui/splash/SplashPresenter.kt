package com.interco.e.daggerrxretrofit.ui.splash

import com.interco.e.daggerrxretrofit.base.presenter.BaseActivityPresenter
import io.reactivex.functions.Consumer
import timber.log.Timber

/**
 * Created by emine on 04/01/2019.
 */
open class SplashPresenter : BaseActivityPresenter<SplashViewInterface, SplashRepesotery>(SplashRepesotery()) {

    fun requestValidToken() {
        viewCallback.toggleLoading(true)

        if (!repository.verifyValiidityToken()) {
            subscribeMainThredSingle(repository.requestToken(),
                    Consumer {
                        viewCallback.toggleLoading(false)
                        viewCallback.navigateTohome()
                    },
                    Consumer { error ->
                        viewCallback.toggleLoading(false)
                        viewCallback.showMessageError(error.localizedMessage)
                        Timber.e(error)
                    })
        } else {
            viewCallback.navigateTohome()
        }


    }


}