package com.example.restaurantsearch.models.menu_model


import com.google.gson.annotations.SerializedName

data class Category(
    val id: String,
    @SerializedName("menu-items")
    val menuItems: ArrayList<MenuItems>,
    val name: String
)