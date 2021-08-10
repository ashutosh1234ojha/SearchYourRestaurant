package com.example.restaurantsearch.utils

import android.content.Context
import androidx.room.Room
import com.example.restaurantsearch.db.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object AppRepositoryBuilder {
    var appRepository: AppRepository? = null
    fun getInstance(context: Context): AppRepository {
        if (appRepository == null) {
            appRepository = AppRepositoryImpl(
                provideAppLocalDataSource(
                    DatabaseBuilder.getInstance(context = context),
                    Dispatchers.IO
                )
            )

        }
        return appRepository as AppRepository
    }

    private fun provideAppLocalDataSource(
        database: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ): DatabaseHelper {
        return DatabaseHelperImpl(
            database.appDao(), ioDispatcher
        )
    }

}