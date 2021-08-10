package com.example.restaurantsearch.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantsearch.R
import com.example.restaurantsearch.models.menu_model.MenuItems


class MenuAdapter(private var dataSet: ArrayList<MenuItems>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.menuName)
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_menu, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.textView.text = dataSet[position].name
    }

    override fun getItemCount(): Int {
        if (dataSet.isNotEmpty()) {
            return dataSet.size
        } else {
            return 0
        }
    }


}
