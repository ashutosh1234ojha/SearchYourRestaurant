package com.example.restaurantsearch.ui.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantsearch.R
import com.example.restaurantsearch.models.restaurant_model.Restaurant
import com.example.restaurantsearch.models.restaurant_model.Review
import kotlin.math.roundToInt


class RestaurantAdapter(
    private var dataSet: List<Restaurant>,
    private val restaurantClick: RestaurantClick
) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restaurantName: TextView = view.findViewById(R.id.restaurantName)
        val restaurantReview: TextView = view.findViewById(R.id.restaurantReview)
        val rlRestaurant: RelativeLayout = view.findViewById(R.id.rlRestaurant)


    }

   fun setData(dataSet1: List<Restaurant>){
       this.dataSet=dataSet1
        notifyDataSetChanged()
   }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_restaurant, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.restaurantName.text = dataSet.get(position).name
//        calculateReviews(dataSet.get(position).reviews)
        viewHolder.restaurantReview.text = "Average Rating ${calculateReviews(dataSet.get(position).reviews)}"
        viewHolder.rlRestaurant.setOnClickListener {
            restaurantClick.onRestaurantClick(dataSet.get(position).id, dataSet.get(position).name)
        }
    }

    private fun calculateReviews(reviews: ArrayList<Review>): Int {
        var total=0.0;
        for (review in reviews){
            total += review.rating

        }
        return (total / reviews.size).roundToInt()
    }

    override fun getItemCount() = dataSet.size

}
