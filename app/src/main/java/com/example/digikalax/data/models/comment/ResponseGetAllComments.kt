package com.example.digikalax.data.models.comment


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ResponseGetAllComments(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: String,
    @SerializedName("success")
    var success: Boolean
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("createdAt")
        var createdAt: String,
        @SerializedName("description")
        var description: String,
        @SerializedName("_id")
        var id: String,
        @SerializedName("productId")
        var productId: String,
        @SerializedName("star")
        var star: Int?,
        @SerializedName("start")
        var start: Int?,
        @SerializedName("title")
        var title: String,
        @SerializedName("updatedAt")
        var updatedAt: String,
        @SerializedName("userId")
        var userId: String,
        @SerializedName("userName")
        var userName: String,
        @SerializedName("__v")
        var v: Int
    ) : Parcelable


}