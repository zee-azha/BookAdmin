package com.example.adminbookstore

import androidx.recyclerview.widget.DiffUtil
import com.example.adminbookstore.data.ResultBooks

class MyDiffUtil (
    private val oldList: List<ResultBooks>,
    private val newList: List<ResultBooks>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].bookId == newList[newItemPosition].bookId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val  mOldList = oldList[oldItemPosition]
        val  mNewList = newList[newItemPosition]

        return  mOldList.bookId == mNewList.bookId && mOldList.title == mNewList.title && mOldList.author == mNewList.author && mOldList.status == mNewList.status && mOldList.price == mNewList.price
    }
}