package com.interco.e.daggerrxretrofit.ui.splash

import com.comuto.authent.data.model.ApiToken
import com.interco.e.daggerrxretrofit.base.repository.BaseRepository
import io.reactivex.Observable
import io.reactivex.Single

class SplashRepesotery : BaseRepository() {


    internal fun requestToken(): Single<ApiToken> {
        return networkApiService.requestToken().doOnSuccess {
            preferencesHelper.putString("token", it.accessToken)
            preferencesHelper.putString("token_duration", it.expiresIn.toString()) // update putlong in prefs provider
        }
    }

    internal fun verifyValiidityToken(): Boolean {

        /*
             return if (preferencesHelper!!.getString("token") == null) {
            false
        } else preferencesHelper!!.getString("token_duration")!!.toLong() <= 446464646464646464
         */
        //446464646464646464 is date todayAslongFormat ( sorry no time to create that func yet )

        return false

    }

}
