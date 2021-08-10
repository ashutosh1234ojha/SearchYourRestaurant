package com.example.restaurantsearch.models.restaurant_model


import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "restaurant_table"
)
data class Restaurant(
    var address: String,
    @SerializedName("cuisine_type")
    var cuisineType: String,
    @PrimaryKey @ColumnInfo(name = "id", index = true)var id: Int,
    @Embedded var latlng: Latlng,
    var name: String,
    var neighborhood: String,
    @Embedded @SerializedName("operating_hours")
    var operatingHours: OperatingHours,
    var photograph: String,
    var reviews: ArrayList<Review>
)
//@Entity(tableName = "restaurant_table")
//data class Restaurant (
//    var address: String,
//    @SerializedName("cuisine_type")
//    var cuisineType: String,
//    @PrimaryKey var id: Int,
//    @Embedded var latlng: Latlng,
//    var name: String,
//    var neighborhood: String,
//    @Embedded @SerializedName("operating_hours")
//    var operatingHours: OperatingHours,
//    var photograph: String,
//)