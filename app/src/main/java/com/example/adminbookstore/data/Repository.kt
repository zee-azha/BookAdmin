package com.example.adminbookstore.data


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.adminbookstore.Api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Result

class Repository constructor(private val apiService: ApiService){

    private val _list = MutableLiveData<List<ResultBooks>>()
    val list: LiveData<List<ResultBooks>> = _list

    fun getContact() {
        val client = apiService.getContact()
        client.enqueue(object: Callback<List<ResultBooks>> {
            override fun onResponse(
                call: Call<List<ResultBooks>>,
                response: Response<List<ResultBooks>>
            ) {

                if (response.isSuccessful){
                    _list.value = response.body()

                }
                else{

                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ResultBooks>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    suspend fun addContact(
        title: String,
        year: Int,
        author: String,
        publisher: String,
        status: String,
        price: Int
    ): Flow<Result<BookResponse>> = flow {
        try {
            val response = apiService.addContact(title, year, author, publisher, status, price)
            emit(Result.success(response))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

    suspend fun updateContact(
        id: Int,
        title: String,
        year: Int,
        author: String,
        publisher: String,
        status: String,
        price: Int
    ):Flow<Result<BookResponse>> = flow {
        try {
            val response = apiService.updateContact(id, title, year, author, publisher, status, price)
            emit(Result.success(response))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

    suspend fun deleteContact(
        id: Int,
        contactId: Int
    ):Flow<Result<BookResponse>> = flow {
        try {
            val response = apiService.deleteContact(id,contactId)
            emit(Result.success(response))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }



    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService,

            ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService)
            }.also { instance = it }
    }
}