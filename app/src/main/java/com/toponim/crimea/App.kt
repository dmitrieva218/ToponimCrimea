package com.toponim.crimea

import android.app.Application
import androidx.room.Room
import com.toponim.crimea.database.AppDataBase
import com.toponim.crimea.database.AppDataBase.Companion.DATABASE_NAME

class App : Application() {
    companion object {
        lateinit var instance: App private set
    }

    lateinit var db: AppDataBase
    override fun onCreate() {
        super.onCreate()
        instance = this

        db = Room.databaseBuilder(applicationContext, AppDataBase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }
}