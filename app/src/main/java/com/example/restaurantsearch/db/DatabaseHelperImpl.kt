package com.example.restaurantsearch.db

import com.example.restaurantsearch.models.menu_model.Menu
import com.example.restaurantsearch.models.restaurant_model.Restaurant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseHelperImpl internal constructor(
    private val appDao: RestaurantDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DatabaseHelper {
    override suspend fun getAllResturant(): List<Restaurant> =
        withContext(ioDispatcher) {
            return@withContext try {
                val restaurants = mutableListOf<Restaurant>()
                val restaurantInner = appDao.getAllRestaurants()

                for (restaurant in restaurantInner) {
                    restaurants.add(restaurant)
                }

                restaurants
            } catch (e: Exception) {
                e.message?.let {
                    mutableListOf<Restaurant>()
                } ?: run {
                    mutableListOf<Restaurant>()
                }

            }
        }

    override suspend fun getRestaurantBySearch(query:String): List<Restaurant> =
        withContext(ioDispatcher) {
            return@withContext try {
                val restaurants = mutableListOf<Restaurant>()
                val restaurantInner = appDao.getRestaurantByName(query)

                for (restaurant in restaurantInner) {
                    restaurants.add(restaurant)
                }
                restaurants
            } catch (e: Exception) {
                e.message?.let {
//                    ApiResult.Error(ApiError(2000, it))
                    mutableListOf<Restaurant>()
                } ?: run {
                    mutableListOf<Restaurant>()
                }

            }
        }

    override suspend fun getAllMenu(): List<Menu> =
        withContext(ioDispatcher) {
            return@withContext try {
                val restaurants = mutableListOf<Menu>()
                val restaurantInner = appDao.getAllMenu()

                for (restaurant in restaurantInner) {
                    restaurants.add(restaurant)
                }

                restaurants
            } catch (e: Exception) {
                e.message?.let {
                    mutableListOf<Menu>()
                } ?: run {
                    mutableListOf<Menu>()
                }

            }
        }

    override suspend fun getAllMenuByRestaurantId(id: Int): List<Menu> =
        withContext(ioDispatcher) {
            return@withContext try {
                val menus = mutableListOf<Menu>()
                //   val restaurantInner1 = appDao.getAllMenu()
                val menuInner = appDao.getAllMenuWithId(id)

                for (menu in menuInner) {
                    menus.add(menu)
                }
                menus
            } catch (e: Exception) {
                e.message?.let {
                    mutableListOf<Menu>()
                } ?: run {
                    mutableListOf<Menu>()
                }

            }
        }




    override suspend fun saveRestaurantsDb(restaurantList: List<Restaurant>) {

        for (data in restaurantList) {
            appDao.insertRestaurant(data)
        }
    }

    override suspend fun saveMenuDb(menu: List<Menu>) {
        for (data in menu) {
            appDao.insertMenu(data)
        }
    }


}