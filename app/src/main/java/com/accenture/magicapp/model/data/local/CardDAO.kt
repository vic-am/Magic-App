package com.accenture.magicapp.model.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.accenture.magicapp.model.data.entities.CardItemEntity
import io.reactivex.Observable

@Dao
interface CardDAO {

    @Insert
    fun saveCard(card: CardItemEntity)

    @Delete
    fun removeCard(card: CardItemEntity)

    @Query("SELECT * FROM Card")
    fun loadCards(): Observable<List<CardItemEntity>>

}