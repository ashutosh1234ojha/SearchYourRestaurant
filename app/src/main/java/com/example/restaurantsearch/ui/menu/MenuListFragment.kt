package com.example.restaurantsearch.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantsearch.R
import com.example.restaurantsearch.models.menu_model.MenuItems
import kotlin.collections.ArrayList

class MenuListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_menu_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("ARG_OBJECT") }?.apply {
//            val textView: TextView = view.findViewById(android.R.id.text1)
            var list = getSerializable("ARG_OBJECT") as ArrayList<MenuItems>
            setUpView(view,list)
        }


    }

    private fun setUpView(view: View?, list: ArrayList<MenuItems>) {
        val adapter = MenuAdapter(list!!)

       // adapter.setData(list)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter

    }
}