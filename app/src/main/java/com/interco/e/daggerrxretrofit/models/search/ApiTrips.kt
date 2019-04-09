package com.comuto.search.data.model

data class ApiTrips(
    val pager: ApiPager,
    val trips: List<ApiTrip>
)
