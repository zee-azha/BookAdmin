package com.example.adminbookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.adminbookstore.ViewModel.MainViewModel
import com.example.adminbookstore.ViewModel.ViewModelFactory
import com.example.adminbookstore.databinding.ActivityAddContactBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddContactBinding
    private var job: Job = Job()
    private  val viewModel: MainViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            addContact()
        }
    }


     private fun addContact(){
        val title =binding.edTitle.text.toString().trim()
        val year = binding.edYear.text.toString().toInt()
        val author = binding.edAuthor.text.toString().trim()
        val publisher = binding.edPublisher.text.toString().trim()
        val price = binding.edPrice.text.toString().toInt()
        val status  = "Ready"


        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.addContact(title,year,author,publisher,status,price).collect{
                it.onSuccess {
                    Toast.makeText(this@AddContactActivity, "Data Berhasil ditambahkan", Toast.LENGTH_LONG).show()
                    intent = Intent(this@AddContactActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                }
            }
        }
    }



}