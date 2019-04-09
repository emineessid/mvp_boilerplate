package com.interco.e.daggerrxretrofit.utils

import com.interco.e.daggerrxretrofit.NETWORK.NetworkApiService
import com.interco.e.daggerrxretrofit.root.App
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor() : okhttp3.Authenticator {


    @Inject
    lateinit var apiService: NetworkApiService

    override fun authenticate(route: Route?, response: Response): Request? {

        App.appComponant!!.inject(this)

        val refreshResult = apiService.requestToken().blockingGet()

        return if (refreshResult != null && !refreshResult.accessToken.isEmpty()) {
            //save new access and refresh token
            // than create a new request and modify it accordingly using the new token
            response.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${refreshResult.accessToken}")
                    .build()

        } else {
            //we got empty response and return null
            //if we dont return null this method is trying to make so many request
            //to get new access token
            Timber.e(" **** failed to request new token from Authenticator ***")
            null

        }
    }
}