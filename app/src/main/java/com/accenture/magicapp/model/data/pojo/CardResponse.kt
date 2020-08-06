package com.accenture.magicapp.model.data.pojo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class CardResponse(
    val cards: List<CardsItem>? = null
)

@Parcelize
data class CardsItem(
    var cardId: Int? = null,
    var imageUrl: String? = null,
    val id: String? = null,
    val types: List<String?>? = null,
    val name: String? = null,
    val text: String? = null,
    val set: String? = null,
    var itemViewIdentify: String? = "card",
    val foreignNames: List<ForeignNamesItem?>? = null
) : Parcelable {


}

@Parcelize
data class ForeignNamesItem(
    val flavor: String? = null,
    val multiverseid: Int? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val language: String? = null,
    val text: String? = null
) : Parcelable
