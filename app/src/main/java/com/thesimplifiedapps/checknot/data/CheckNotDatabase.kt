package com.thesimplifiedapps.checknot.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thesimplifiedapps.checknot.MyApplication
import com.thesimplifiedapps.checknot.data.dao.CheckNotDao
import com.thesimplifiedapps.checknot.data.entities.Note

@Database(entities = [Note::class], version = CheckNotDatabase.VERSION)
abstract class CheckNotDatabase : RoomDatabase() {

    abstract fun checkNotDao(): CheckNotDao

    companion object {
        const val VERSION = 1
        private const val NAME = "check_not_database"

        @Volatile
        private var INSTANCE: CheckNotDatabase? = null

        fun getDatabase(): CheckNotDatabase {
            synchronized(this) {
                INSTANCE?.let {
                    return it
                } ?: return Room.databaseBuilder(
                    MyApplication.myApplicationContext, CheckNotDatabase::class.java, NAME
                ).fallbackToDestructiveMigration()
                    .build()

            }
        }
    }
}