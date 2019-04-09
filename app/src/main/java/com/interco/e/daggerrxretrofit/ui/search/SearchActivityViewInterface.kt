package com.interco.e.daggerrxretrofit.ui.search

import com.comuto.search.data.model.ApiTrips
import com.interco.e.daggerrxretrofit.base.activity.BaseActivityViewInterface

interface SearchActivityViewInterface : BaseActivityViewInterface {

    fun showErrorGetLocations(message:String)
    fun showSuccsesgetLocations( result:ApiTrips)

}
