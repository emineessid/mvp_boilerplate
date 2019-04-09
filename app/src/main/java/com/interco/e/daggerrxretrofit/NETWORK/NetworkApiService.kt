package com.interco.e.daggerrxretrofit.NETWORK

import com.comuto.authent.data.model.ApiAuthent
import com.comuto.authent.data.model.ApiToken
import com.comuto.search.data.model.ApiTrips
import com.google.gson.Gson
import com.interco.e.daggerrxretrofit.BuildConfig
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by emine on 14/12/2018.
 */
@Singleton
open class NetworkApiService @Inject
constructor(var gson: Gson, var retrofit: Retrofit) {

    private  val networkApi: NetworkApi = retrofit.create(NetworkApi::class.java)

    fun requestToken(): Single<ApiToken> {
        val apiAuthent = ApiAuthent(BuildConfig.grantType, BuildConfig.client_id, BuildConfig.client_secret)
        return networkApi.getToken(apiAuthent)
    }

    fun requestLocations(token: String, departure: String, arrival: String): Observable<ApiTrips> {
        return networkApi.getDesiredLocation(
                token,
                "json",
                Locale.getDefault().country,
                "EUR",
                departure,
                arrival)
    }


}
