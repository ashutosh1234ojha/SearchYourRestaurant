package com.example.restaurantsearch.db

import androidx.room.*
import com.example.restaurantsearch.models.SearchResult
import com.example.restaurantsearch.models.menu_model.Menu
import com.example.restaurantsearch.models.relation.RestaurantWithMenu
import com.example.restaurantsearch.models.restaurant_model.Restaurant

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant_table")
    suspend fun getAllRestaurants(): List<Restaurant>

    @Query("SELECT * FROM menu_table")
    suspend fun getAllMenu(): List<Menu>

    @Query("SELECT * FROM menu_table WHERE restaurantId =:query")
    suspend fun getAllMenuWithId(query: Int): List<Menu>

    @Query("SELECT * FROM restaurant_table WHERE name LIKE '%' || :query || '%'  OR cuisineType LIKE '%' || :query || '%'")
    suspend fun getRestaurantByName(query: String): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: Restaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(restaurant: Menu)


}