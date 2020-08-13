package com.example.android_word_book_app.data

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao) {

    val readAllData: LiveData<List<Word>> = wordDao.getAllData()

    suspend fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }

    suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }

    suspend fun deleteAllWords() {
        wordDao.deleteAllWords()
    }
}