package com.comuto.authent.data.model

data class ApiToken(
    val accessToken: String,
    val tokenType: String,
    val issuedAt: Long,
    val expiresIn: Long,
    val clientId: String,
    val scopes: List<String>?
)