package com.example.adminbookstore.Api


import com.example.adminbookstore.data.BookResponse
import com.example.adminbookstore.data.ResultBooks
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("book")
     fun getContact(
     ):Call<List<ResultBooks>>
     @FormUrlEncoded

     @POST("book")
     suspend fun addContact(
         @Field("title") title: String,
         @Field("year") year: Int,
         @Field("author") author: String,
         @Field("publisher") publisher: String,
         @Field("status") status: String,
         @Field("price") price: Int
     ): BookResponse

     @FormUrlEncoded
     @PUT("book/{id}")
     suspend fun updateContact(
        @Path("id") id: Int,
        @Field("title") title: String,
        @Field("year") year: Int,
        @Field("author") author: String,
        @Field("publisher") publisher: String,
        @Field("status") status: String,
        @Field("price") price: Int
     ): BookResponse


     @FormUrlEncoded
     @HTTP(method = "DELETE", path = "book/{id}", hasBody = true)
     suspend fun deleteContact(@Path("id")id: Int,@Field("book_id") bookId: Int): BookResponse


}