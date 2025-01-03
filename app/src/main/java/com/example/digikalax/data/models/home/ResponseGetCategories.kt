package com.example.digikalax.data.models.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class ResponseGetCategories(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: String,
    @SerializedName("success")
    var success: Boolean
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("_id")
        var id: String,
        @SerializedName("image")
        var image: String,
        @SerializedName("name")
        var name: String
    ) : Parcelable
}