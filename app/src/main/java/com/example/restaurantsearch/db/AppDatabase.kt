package com.example.restaurantsearch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restaurantsearch.models.menu_model.Menu
import com.example.restaurantsearch.models.restaurant_model.Restaurant


@Database(entities = [Restaurant::class, Menu::class], version = 1, exportSchema = false)
@TypeConverters(ReviewsConverters::class, CategoryConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): RestaurantDao

}