package com.toponim.crimea.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toponim.crimea.dataclass.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM words_table")
    fun getAllWords(): List<Word>

    @Query("SELECT * FROM words_table WHERE word LIKE  '%' || :word || '%'")
    fun getCurrentWord(word: String?): List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllWord(words: List<Word>)
}