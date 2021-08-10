package com.example.restaurantsearch.ui

import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import com.example.restaurantsearch.MyApplication
import com.example.restaurantsearch.db.AppRepository


class BaseViewModelFactory(appRepository: AppRepository, application: MyApplication) :
    ViewModelProvider.Factory {
    private val mApplication: MyApplication = application
    private val appRepository: AppRepository = appRepository
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BaseViewModel(appRepository, mApplication) as T
    }

}