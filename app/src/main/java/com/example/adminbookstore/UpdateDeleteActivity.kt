package com.example.adminbookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.adminbookstore.ViewModel.MainViewModel
import com.example.adminbookstore.data.ResultBooks
import com.example.adminbookstore.ViewModel.ViewModelFactory
import com.example.adminbookstore.databinding.ActivityUpdateDeleteBinding
import kotlinx.coroutines.launch

class UpdateDeleteActivity : AppCompatActivity() {
    private var status: String? = null
    private lateinit var binding: ActivityUpdateDeleteBinding
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contact = intent.getParcelableExtra<ResultBooks>(EXTRA_CONTACT)
        showContact(contact!!)
        binding.switchMaterial.isChecked= status.equals("Ready")
        binding.btnAdd.setOnClickListener {
            updateContact()
        }
    }

    private fun showContact(contact: ResultBooks) {
        binding.apply {
            if (contact != null) {

                edTitle.setText(contact.title)
                edPrice.setText(contact.price.toString())
                edAuthor.setText(contact.author)
                edPublisher.setText(contact.publisher)
                edYear.setText(contact.year.toString())
                status = contact.status
                switchMaterial.isChecked= status.equals("Ready")

            }
        }


    }

    private fun updateContact() {
        val title =binding.edTitle.text.toString().trim()
        val year = binding.edYear.text.toString().toInt()
        val author = binding.edAuthor.text.toString().trim()
        val publisher = binding.edPublisher.text.toString().trim()
        val price = binding.edPrice.text.toString().toInt()
        status = if (binding.switchMaterial.isChecked){
            "Ready"
        }else{
            "Out Of Stock"
        }

        val contact = intent.getParcelableExtra<ResultBooks>(EXTRA_CONTACT)
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.updateContact(contact!!.bookId!!.toInt(), title, year, author, publisher, status.toString(), price).collect {
                    it.onSuccess {
                        Toast.makeText(
                            this@UpdateDeleteActivity,
                            "Data Berhasil Diubah",
                            Toast.LENGTH_LONG
                        ).show()
                        intent = Intent(this@UpdateDeleteActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    it.onFailure { task ->
                        Toast.makeText(
                            this@UpdateDeleteActivity,
                            task.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


    private fun deleteContact() {
        val contact = intent.getParcelableExtra<ResultBooks>(EXTRA_CONTACT)
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.deleteContact(contact!!.bookId!!.toInt(),contact!!.bookId!!.toInt()).collect {
                    it.onSuccess {
                        Toast.makeText(
                            this@UpdateDeleteActivity,
                            "Data Berhasil Dihapus",
                            Toast.LENGTH_LONG
                        ).show()
                        intent = Intent(this@UpdateDeleteActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    it.onFailure { task ->
                        Toast.makeText(
                            this@UpdateDeleteActivity,
                            task.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_delete, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dialogTitle: String
        val dialogMessage: String

        dialogMessage = "Apakah anda yakin ingin menghapus item ini?"
        dialogTitle = "hapus"

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton("ya") { _, _ ->

                deleteContact()
            }
            setNegativeButton("tidak") { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()


        return super.onOptionsItemSelected(item)
    }



    companion object {
        const val EXTRA_CONTACT= "extra_contact"
    }



}