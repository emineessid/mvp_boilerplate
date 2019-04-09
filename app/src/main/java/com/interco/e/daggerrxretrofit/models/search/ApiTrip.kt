package com.comuto.search.data.model

data class ApiTrip(
    val user: ApiUser? = null,
    val departureDate: String? = null,
    val departurePlace: ApiPlace? = null,
    val arrivalDate: ApiPlace? = null,
    val arrivalPlace: ApiPlace? = null,
    val price: ApiPrice? = null
)
