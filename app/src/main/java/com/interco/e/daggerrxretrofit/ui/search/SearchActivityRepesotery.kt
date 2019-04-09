package com.interco.e.daggerrxretrofit.ui.search

import com.comuto.search.data.model.ApiTrips
import com.interco.e.daggerrxretrofit.base.repository.BaseRepository
import io.reactivex.Observable

class SearchActivityRepesotery : BaseRepository() {

    fun requestLocations(departure: String, arrival: String): Observable<ApiTrips> {
        val token = preferencesHelper.getString("token")

        return if ( token != null){
            networkApiService.requestLocations(token ,departure,arrival)
        }else {
            Observable.error(Throwable(" no token available"))
        }


    }

}
