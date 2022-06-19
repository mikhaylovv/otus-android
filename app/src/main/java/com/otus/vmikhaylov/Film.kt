package com.otus.vmikhaylov

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film (
    val image: Int,
    val title: String,
    val description: String,
    var selected: Boolean,
    var favorite: Boolean,
    ) : Parcelable
