package com.example.restaurantsearch.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantsearch.MainActivity
import com.example.restaurantsearch.MyApplication
import com.example.restaurantsearch.R
import com.example.restaurantsearch.models.restaurant_model.Restaurant
import com.example.restaurantsearch.ui.BaseViewModel
import com.example.restaurantsearch.ui.BaseViewModelFactory
import com.example.restaurantsearch.utils.AppRepositoryBuilder
import java.util.*

class RestaurantFragment : Fragment(), RestaurantClick {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurants, container, false)
        setUpView(view)
        return view
    }

    private fun setUpView(view: View?) {
        val appRepository = AppRepositoryBuilder.getInstance(requireContext())
        val myViewModel: BaseViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(appRepository, this.activity?.application as MyApplication)
        ).get(
            BaseViewModel::class.java
        )
        myViewModel.saveRestaurantDataInDatabase()
        var list = ArrayList<Restaurant>()
        val adapter = RestaurantAdapter(list!!, this)

        myViewModel.restaurantLiveData.observe(requireActivity(), androidx.lifecycle.Observer {
            list = it as ArrayList<Restaurant>
            adapter.setData(list)
        })
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }


    override fun onRestaurantClick(id: Int, name: String) {
        val bundle = Bundle()
        bundle.putInt("restaurantId", id)
        bundle.putString("restaurantName", name)


        val navController = (activity as MainActivity?)?.navController

        navController?.navigate(R.id.action_restaurantFragment_to_menuFragment, bundle)
    }
}

interface RestaurantClick {
    fun onRestaurantClick(id: Int, name: String)
}