package com.xpridex.domain.entity


data class PagedResponseEntity<T>(
    val page: Int = 0,
    val totalResults: Int = 0,
    val totalPages: Int = 0,
    val results: List<T> = ArrayList()
)