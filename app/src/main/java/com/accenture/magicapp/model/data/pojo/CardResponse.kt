package com.accenture.magicapp.model.data.pojo

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class CardResponse(
    val cards: List<CardsItem>? = null
)


@Entity(tableName = "Card")
class CardsItem() : Parcelable {

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "card_id")
    val cardId: Int? = null

	@ColumnInfo(name = "image_url")
	val imageUrl: String? = null

	val id: String? = null
	var type: String? = null
    var text: String? = null
    val types: List<String?>? = null
    val set: String? = null
    val name: String? = null
    var itemViewIdentify: String? = "card"
    val setName: String? = null
    val foreignNames: List<ForeignNamesItem?>? = null
    val originalType: String? = null

    constructor(parcel: Parcel) : this() {
        type = parcel.readString()
        text = parcel.readString()
        itemViewIdentify = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(text)
        parcel.writeString(itemViewIdentify)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardsItem> {
        override fun createFromParcel(parcel: Parcel): CardsItem {
            return CardsItem(parcel)
        }

        override fun newArray(size: Int): Array<CardsItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class ForeignNamesItem(
    val flavor: String? = null,
    val multiverseid: Int? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val language: String? = null,
    val text: String? = null
)
