package com.accenture.magicapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.accenture.magicapp.domain.model.CardItemEntity

@Database(entities = [CardItemEntity::class], version = 1, exportSchema = false)
abstract class CardDatabase : RoomDatabase() {

    abstract fun cardDAO(): CardDAO

    companion object {

        private lateinit var INSTANCE: CardDatabase
        fun getDatabase(context: Context): CardDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(CardDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, CardDatabase::class.java, "CardDB")
                        .allowMainThreadQueries()
                        .build()
                }

            }
            return INSTANCE
        }
    }

}