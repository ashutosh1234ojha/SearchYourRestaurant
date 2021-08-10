package com.example.restaurantsearch.db

import androidx.room.TypeConverter
import com.example.restaurantsearch.models.menu_model.Category
import com.example.restaurantsearch.models.restaurant_model.Review
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


object CategoryConverters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<Category> {
        val listType: Type = object : TypeToken<ArrayList<Category?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Category?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }


}