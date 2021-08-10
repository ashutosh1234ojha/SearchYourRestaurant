package com.example.restaurantsearch.models.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.restaurantsearch.models.menu_model.Menu
import com.example.restaurantsearch.models.restaurant_model.Restaurant

data class RestaurantWithMenu(
    val a:String
//    @Embedded val restaurant: Restaurant,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "restaurantId"
//    )
//    val menuList: Menu
)
