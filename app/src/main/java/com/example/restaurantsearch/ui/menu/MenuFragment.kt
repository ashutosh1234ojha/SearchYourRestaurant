package com.example.restaurantsearch.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.restaurantsearch.MyApplication
import com.example.restaurantsearch.R
import com.example.restaurantsearch.models.menu_model.Menu
import com.example.restaurantsearch.ui.BaseViewModel
import com.example.restaurantsearch.ui.BaseViewModelFactory
import com.example.restaurantsearch.utils.AppRepositoryBuilder
import com.google.android.material.tabs.TabLayout
import java.util.*

class MenuFragment : Fragment() {
    private lateinit var categoryPagerAdapter: CategoryPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_menu, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = view.findViewById(R.id.pager)
        categoryPagerAdapter = CategoryPagerAdapter(childFragmentManager)

        viewPager.adapter = categoryPagerAdapter

        tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)


        setUpView(view)

    }

    private fun setUpView(view: View?) {


        val restaurantId = arguments?.getInt("restaurantId")    //in fragment to get  the  value

        val appRepository = AppRepositoryBuilder.getInstance(requireContext())


        val myViewModel: BaseViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(appRepository, this.activity?.application as MyApplication)
        ).get(
            BaseViewModel::class.java
        )

        myViewModel.getRestaurantMenu(restaurantId!!)
        var list = ArrayList<Menu>()

        myViewModel.menuLiveData.observe(requireActivity(), androidx.lifecycle.Observer {
            list = it as ArrayList<Menu>
            categoryPagerAdapter.setData(list)


        })


    }
}