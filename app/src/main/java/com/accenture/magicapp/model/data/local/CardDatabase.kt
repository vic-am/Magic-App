package com.accenture.magicapp.model.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class CardDatabase : RoomDatabase() {

    abstract fun CardDao(): CardDAO

    companion object {

        private lateinit var INSTANCE: CardDatabase
        fun getDatabase(context: Context): CardDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, CardDatabase::class.java, "CardDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }

}