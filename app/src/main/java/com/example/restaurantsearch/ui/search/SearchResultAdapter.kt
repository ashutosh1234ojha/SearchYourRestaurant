package com.example.restaurantsearch.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantsearch.R
import com.example.restaurantsearch.models.SearchResult


class SearchResultAdapter(
    private var dataSet: List<SearchResult>,
) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.menuName)
    }

    fun setData(dataSet1: List<SearchResult>) {
        this.dataSet = dataSet1
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_search, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet.get(position).restaurantName
    }

    override fun getItemCount() = dataSet.size

}
