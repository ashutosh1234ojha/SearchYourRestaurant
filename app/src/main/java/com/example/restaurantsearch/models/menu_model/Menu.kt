package com.example.restaurantsearch.models.menu_model


import androidx.room.*
import com.example.restaurantsearch.models.restaurant_model.Restaurant
import com.google.gson.annotations.SerializedName

//@Entity(
//    tableName = "menu_table",
//    indices =[@Index("id")],
//    foreignKeys = [ForeignKey(
//        entity = Restaurant::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("restaurantId"),
//        onDelete = ForeignKey.CASCADE
//    )]
//)
@Entity(
    tableName = "menu_table",
//    foreignKeys = [ForeignKey(
//        entity = Restaurant::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("restaurantId"),
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class Menu(
    @PrimaryKey(autoGenerate = true) val menuId: Int,
    val categories: ArrayList<Category>,
    val restaurantId: Int,

    )