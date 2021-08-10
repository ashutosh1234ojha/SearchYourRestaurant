package com.example.restaurantsearch.db


import com.example.restaurantsearch.models.menu_model.Menu
import com.example.restaurantsearch.models.restaurant_model.Restaurant

interface DatabaseHelper {
    suspend fun getAllResturant(): List<Restaurant>
    suspend fun getAllMenu(): List<Menu>
    suspend fun getAllMenuByRestaurantId(id:Int): List<Menu>
    suspend fun getRestaurantBySearch(query:String): List<Restaurant>
    suspend fun saveRestaurantsDb(restaurantList: List<Restaurant>)
    suspend fun saveMenuDb(restaurantList: List<Menu>)

}