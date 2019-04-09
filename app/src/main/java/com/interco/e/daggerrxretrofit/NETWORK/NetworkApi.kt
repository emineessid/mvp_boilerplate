package com.interco.e.daggerrxretrofit.NETWORK

import com.comuto.authent.data.model.ApiAuthent
import com.comuto.authent.data.model.ApiToken
import com.comuto.search.data.model.ApiTrips
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by emine on 14/12/2018.
 */
interface NetworkApi {

    @Headers("Content-Type: application/json")
    @POST(BaseRoutes.TOKEN)
    fun getToken(@Body apiAuth: ApiAuthent): Single<ApiToken>

    @Headers("Content-Type: application/json")
    @GET(BaseRoutes.GET_LOCATIONS)
    fun getDesiredLocation(
            @Header("Authorization")  token:String, // ADD TO CONST HEADERS
            @Query("_format") format: String,
            @Query("locale") locale: String,
            @Query("cur") cur: String,
            @Query("fn") departure: String,
            @Query("tn") arrival: String

    ): Observable<ApiTrips>


}
