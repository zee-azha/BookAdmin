package com.example.adminbookstore

import android.content.Context
import com.example.BookAdmins.Api.ApiInstance
import com.example.adminbookstore.data.Repository


object Injection {

    fun provideRepository(context: Context): Repository {
        val apiService = ApiInstance.getApiService()


        return Repository.getInstance(apiService)
    }
}