package com.example.restaurantsearch.models

data class SearchResult(
    val restaurantId: Int,
    val restaurantName: String,
    val menuId: Int,
    val menuName: String,
    val isRestaurant: Boolean
)
