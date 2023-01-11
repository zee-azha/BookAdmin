package com.example.adminbookstore.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminbookstore.data.Repository
import com.example.adminbookstore.data.ResultBooks
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository): ViewModel() {

     val list: LiveData<List<ResultBooks>> = repository.list

     fun getContact() {
          viewModelScope.launch {
               repository.getContact()
          }

     }
     suspend fun addContact(title: String,
                            year: Int,
                            author: String,
                            publisher: String,
                            status: String,
                            price: Int) = repository.addContact(title, year, author, publisher, status, price)

     suspend fun updateContact(id: Int,
                               title: String,
                               year: Int,
                               author: String,
                               publisher: String,
                               status: String,
                               price: Int) = repository.updateContact(id, title, year, author, publisher, status, price)

     suspend fun deleteContact(id: Int,contactId: Int) = repository.deleteContact(id, contactId )
}