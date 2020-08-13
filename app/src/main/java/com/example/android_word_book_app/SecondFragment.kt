package com.example.android_word_book_app

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android_word_book_app.data.Word
import com.example.android_word_book_app.data.WordViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*


class SecondFragment : Fragment() {

    private lateinit var mWordViewModel: WordViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        mWordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        view.button_save.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val editWord = edit_word.text.toString()

        if (!TextUtils.isEmpty(editWord)) {
            val word = Word(0, editWord)
            mWordViewModel.insertWord(word)
            Toast.makeText(requireContext(), "単語を登録しました。", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        } else {
            Toast.makeText(requireContext(), "単語を入力してください。", Toast.LENGTH_SHORT).show()
        }
    }

}
