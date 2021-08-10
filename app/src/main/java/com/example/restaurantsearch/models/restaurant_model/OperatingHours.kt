package com.example.restaurantsearch.models.restaurant_model


import com.google.gson.annotations.SerializedName

data class OperatingHours(
    @SerializedName("Friday")
    var friday: String,
    @SerializedName("Monday")
    var monday: String,
    @SerializedName("Saturday")
    var saturday: String,
    @SerializedName("Sunday")
    var sunday: String,
    @SerializedName("Thursday")
    var thursday: String,
    @SerializedName("Tuesday")
    var tuesday: String,
    @SerializedName("Wednesday")
    var wednesday: String
)