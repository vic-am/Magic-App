package com.accenture.magicapp.model.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.accenture.magicapp.model.data.pojo.CardsItem

@Dao
interface CardDAO {

    @Insert
    fun saveCard(card: CardsItem)

    @Delete
    fun removeCard(card: CardsItem)

    @Query("SELECT * FROM Card")
    fun loadCards()

}