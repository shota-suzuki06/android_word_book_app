package com.example.android_word_book_app.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Query("SELECT * FROM word_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<Word>>

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAllWords()

}