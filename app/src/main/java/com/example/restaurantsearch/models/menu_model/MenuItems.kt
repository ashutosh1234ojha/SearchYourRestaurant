package com.example.restaurantsearch.models.menu_model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MenuItems(
    val description: String,
    val id: String,
    val images: List<Any>,
    val name: String,
    val price: String
)