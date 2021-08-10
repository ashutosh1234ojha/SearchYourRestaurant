package com.example.restaurantsearch.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.restaurantsearch.models.menu_model.Menu

class CategoryPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {

        var menuList = ArrayList<Menu>()
    override fun getItem(i: Int): Fragment {
        val fragment = MenuListFragment()
        fragment.arguments = Bundle().apply {
            putSerializable("ARG_OBJECT", menuList[0].categories[i].menuItems)
        }
        return fragment
    }

    fun setData(list: ArrayList<Menu>) {
        menuList = list
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        if (menuList.size > 0) {
            return menuList.get(0).categories.size
        } else {
            return 0
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return menuList.get(0).categories.get(position).name
    }
}
