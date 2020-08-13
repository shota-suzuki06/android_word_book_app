package com.example.android_word_book_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_word_book_app.R
import com.example.android_word_book_app.data.Word
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.WordViewHolder>() {

    private var wordList = emptyList<Word>()

    class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false))
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentItem = wordList[position]
        holder.itemView.textView.text = currentItem.word.toString()
    }

    fun setData(word: List<Word>) {
        this.wordList = word
        notifyDataSetChanged()
    }

    fun getData(position: Int): Word {
        return this.wordList[position]
    }

}