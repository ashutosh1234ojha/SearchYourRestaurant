package com.example.restaurantsearch.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantsearch.MyApplication
import com.example.restaurantsearch.R
import com.example.restaurantsearch.models.SearchResult
import com.example.restaurantsearch.ui.BaseViewModel
import com.example.restaurantsearch.ui.BaseViewModelFactory
import com.example.restaurantsearch.utils.AppRepositoryBuilder

class SearchFragment : Fragment() {

    var list = ArrayList<SearchResult>()
    lateinit var myViewModel: BaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_search, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etSearch = view.findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(textWatcher)
        setUpView(view)
    }


    private fun setUpView(view: View?) {
        val appRepository = AppRepositoryBuilder.getInstance(requireContext())

         myViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(appRepository, this.activity?.application as MyApplication)
        ).get(
            BaseViewModel::class.java
        )
        var list = java.util.ArrayList<SearchResult>()
        val adapter = SearchResultAdapter(list!!)

        myViewModel.searchLiveData.observe(requireActivity(), androidx.lifecycle.Observer {
            list.clear()
            list = it as java.util.ArrayList<SearchResult>
            adapter.setData(list)
        })
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
    }

    private val textWatcher =
        object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("MYTextWatcher", s.toString())
                myViewModel.saveDataInDatabase(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }


}