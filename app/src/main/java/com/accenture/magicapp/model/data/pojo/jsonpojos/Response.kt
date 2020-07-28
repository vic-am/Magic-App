package com.accenture.magicapp.model.data.pojo.jsonpojos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
	val cards: List<CardsItem>? = null
) : Parcelable

@Parcelize
data class CardsItem(
	val colorIdentity: List<String?>? = null,
	val setName: String? = null,
	val multiverseid: Int? = null,
	val foreignNames: List<ForeignNamesItem?>? = null,
	val originalType: String? = null,
	val artist: String? = null,
	val type: String? = null,
	val colors: List<String?>? = null,
	val number: String? = null,
	val printings: List<String?>? = null,
	val variations: List<String?>? = null,
	val imageUrl: String? = null,
	val text: String? = null,
	val id: String? = null,
	val types: List<String?>? = null,
	val set: String? = null,
	val flavor: String? = null,
	val layout: String? = null,
	val originalText: String? = null,
	val name: String? = null
) : Parcelable

@Parcelize
data class ForeignNamesItem(
	val flavor: String? = null,
	val multiverseid: Int? = null,
	val imageUrl: String? = null,
	val name: String? = null,
	val language: String? = null,
	val text: String? = null
) : Parcelable
