package com.example.restaurantsearch.db


import com.example.restaurantsearch.models.menu_model.Menu
import com.example.restaurantsearch.models.restaurant_model.Restaurant

interface AppRepository {

    suspend fun getResturants(query: String, fromApi: Boolean): List<Restaurant>
    suspend fun getResturantsBySearch(query: String): List<Restaurant>
    suspend fun getMenu(query: String, fromApi: Boolean): List<Menu>
    suspend fun getMenByRestaurantIdu(query: Int, fromApi: Boolean): List<Menu>
    suspend fun saveRestaurantsDb(searchResponse: List<Restaurant>)
    suspend fun saveMenuDb(searchResponse: List<Menu>)

}