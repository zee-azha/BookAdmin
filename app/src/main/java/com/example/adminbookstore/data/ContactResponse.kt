package com.example.adminbookstore.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class BookResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val items: String

)

@Parcelize
data class ResultBooks(
    @field:SerializedName("book_id")
    val bookId: String?,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("year")
    val year: Int?,

    @field:SerializedName("author")
    val author: String?,

    @field:SerializedName("publisher")
    val publisher:String,

    @field:SerializedName("status")
    val status:String,

    @field:SerializedName("price")
    val price: Int





):Parcelable