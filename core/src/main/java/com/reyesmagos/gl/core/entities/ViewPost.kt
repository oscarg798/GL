package com.reyesmagos.gl.core.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ViewPost(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val postBody: String,
    val date: String,
    val imageUrl: String?
) : Parcelable
