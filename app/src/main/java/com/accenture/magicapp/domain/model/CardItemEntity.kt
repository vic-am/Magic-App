package com.accenture.magicapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Card")
data class CardItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var cardId: Int = 0,
    @ColumnInfo
    var imageUrl: String? = null,
    var name: String? = null,
    var itemViewIdentify: String = "card"
) {
}