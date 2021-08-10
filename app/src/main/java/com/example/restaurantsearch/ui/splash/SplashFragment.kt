package com.example.restaurantsearch.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantsearch.MainActivity
import com.example.restaurantsearch.MyApplication
import com.example.restaurantsearch.R
import com.example.restaurantsearch.ui.BaseViewModel
import com.example.restaurantsearch.ui.BaseViewModelFactory
import com.example.restaurantsearch.ui.base.BaseFragment
import com.example.restaurantsearch.utils.AppRepositoryBuilder


class SplashFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   CommonMethods.provideDataBase(requireContext());

        val appRepository = AppRepositoryBuilder.getInstance(requireContext())
        val myViewModel: BaseViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(appRepository, this.activity?.application as MyApplication)
        ).get(
            BaseViewModel::class.java
        )
        myViewModel.saveRestaurantsList()
        myViewModel.saveMenuList()

        myViewModel.itemClickedEvent.observe(requireActivity(), Observer { isItemClicked ->
            if (isItemClicked) {
                Handler(Looper.getMainLooper()).postDelayed({
                    (activity as MainActivity?)?.navController?.navigate(R.id.action_splashFragment_to_restaurantFragment)
                }, 1000)

            }
        })

        return inflater.inflate(R.layout.fragment_splash, container, false)

    }


}