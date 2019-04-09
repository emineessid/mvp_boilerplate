package com.comuto.search.data.model

data class ApiPager(
    val page: Int,
    val pages: Int,
    val total: Int,
    val limit: Int
)