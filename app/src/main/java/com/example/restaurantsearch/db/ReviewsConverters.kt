package com.example.restaurantsearch.db

import androidx.room.TypeConverter
import com.example.restaurantsearch.models.restaurant_model.Review
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object ReviewsConverters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<Review> {
        val listType: Type = object : TypeToken<ArrayList<Review?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Review?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}