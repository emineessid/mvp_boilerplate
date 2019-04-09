package com.comuto.authent.data.model


data class ApiAuthent(
    val grantType: String = "client_credentials",
    val clientId: String,
    val clientSecret: String
   // val scopes: List<String>
)

