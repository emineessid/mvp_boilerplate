package com.interco.e.daggerrxretrofit.utils

import com.interco.e.daggerrxretrofit.NETWORK.NetworkApiService
import com.interco.e.daggerrxretrofit.root.App
import java.io.IOException

import javax.inject.Singleton

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@Singleton
class AuthenticationInterceptor : Interceptor {

    @Inject
    lateinit var apiService: NetworkApiService


    override fun intercept(chain: Interceptor.Chain): Response {
        App.appComponant!!.inject(this)

        val originalRequest = chain.request()
        var modifiedRequest = chain.request()
        val origResponse = chain.proceed(originalRequest)

        // server should give us a 401
        if (origResponse.code() == 401) {
            // start a new synchronous network call to Auth0
            val newIdToken = apiService.requestToken().blockingGet()
            // make a new request with the new id token
            if (newIdToken != null && !newIdToken.accessToken.isEmpty()) {
                modifiedRequest = originalRequest.newBuilder()
                        .header("Authorization", "Bearer ${newIdToken.accessToken}")
                        .build()
            }
            // hopefully we now have a status of 200
            return chain.proceed(modifiedRequest)
        } else {
            return origResponse
        }
    }
}