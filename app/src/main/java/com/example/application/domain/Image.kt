package com.example.application.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val id: String,
    val description: String,
    @SerializedName("alt_description")
    val altDescription: String?,
    val urls: Urls,
    val likes: Int
): Parcelable {

    @Parcelize
    data class Urls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    ): Parcelable
}