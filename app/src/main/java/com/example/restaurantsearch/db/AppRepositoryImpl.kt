package com.example.restaurantsearch.db

import com.example.restaurantsearch.models.menu_model.Menu
import com.example.restaurantsearch.models.restaurant_model.Restaurant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val localDataSource: DatabaseHelper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppRepository {

    override suspend fun getResturants(
        query: String,
        fromApi: Boolean
    ): List<Restaurant> {
        return withContext(ioDispatcher) {
            return@withContext localDataSource.getAllResturant()

        }
    }

    override suspend fun getResturantsBySearch(query: String): List<Restaurant> {
        return withContext(ioDispatcher) {
            return@withContext localDataSource.getRestaurantBySearch(query)

        }
    }

    override suspend fun getMenu(
        query: String,
        fromApi: Boolean
    ): List<Menu> {
        return withContext(ioDispatcher) {
            return@withContext localDataSource.getAllMenu()

        }
    }

    override suspend fun getMenByRestaurantIdu(query: Int, fromApi: Boolean): List<Menu> {
        return withContext(ioDispatcher) {
            return@withContext localDataSource.getAllMenuByRestaurantId(query)

        }
    }

    override suspend fun saveRestaurantsDb(searchResponse: List<Restaurant>) {
        localDataSource.saveRestaurantsDb(searchResponse)

    }

    override suspend fun saveMenuDb(searchResponse: List<Menu>) {
        localDataSource.saveMenuDb(searchResponse)

    }
}