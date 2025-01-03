package com.example.digikalax.data.models.comment


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class BodyResponseSetNewComment(
    @SerializedName("description")
    var description: String,
    @SerializedName("productId")
    var productId: String,
    @SerializedName("star")
    var star: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("token")
    var token: String,
    @SerializedName("userName")
    var userName: String
) : Parcelable