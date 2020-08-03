package com.accenture.magicapp.model.data.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SetResponse(
    val sets: List<Sets>? = null
) : Parcelable

@Parcelize
data class Sets(
    val code: String? = null,
    val releaseDate: String? = null,
    val name: String? = null,
    val onlineOnly: Boolean? = null,
    val block: String? = null,
    val type: String? = null,
    val booster: List<String?>? = null
) : Parcelable
