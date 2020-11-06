package com.toponim.crimea.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toponim.crimea.dataclass.Word

@Database(
    entities = [Word::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        const val DATABASE_NAME: String = "dictionary_db"
    }
}