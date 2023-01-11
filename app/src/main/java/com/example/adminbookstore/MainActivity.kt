package com.example.adminbookstore

import ListContactAdapter
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminbookstore.ViewModel.MainViewModel
import com.example.adminbookstore.data.ResultBooks


import com.example.adminbookstore.ViewModel.ViewModelFactory
import com.example.adminbookstore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  val viewModel: MainViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }
    private lateinit var adapter: ListContactAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListContactAdapter()
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter= adapter
        }
        binding.fabAdd.setOnClickListener {
            intent = Intent(this@MainActivity, AddContactActivity::class.java)
            startActivity(intent)
        }
        adapter.setOnItemClickCallback(object : ListContactAdapter.OnItemClickCallback {
            override fun onItemClicked(contact: ResultBooks) {
                showSelectedContact(contact)
            }
        })
        viewModel.list.observe(this){
        adapter.setData(it as ArrayList<ResultBooks>)
        }
        getContact()

    }

    private fun showSelectedContact(contact: ResultBooks){
        val intentWithExtraData = Intent(this@MainActivity, UpdateDeleteActivity::class.java)
        intentWithExtraData.putExtra(UpdateDeleteActivity.EXTRA_CONTACT,contact)
        startActivity(intentWithExtraData)
    }

    private fun getContact(){
        viewModel.getContact()
    }


}