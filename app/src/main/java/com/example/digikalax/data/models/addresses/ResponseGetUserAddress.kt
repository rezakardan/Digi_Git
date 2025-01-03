package com.example.digikalax.data.models.addresses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class ResponseGetUserAddress(
    @SerializedName("data")
    var `data`: List<Data>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("address")
        var address: String?,
        @SerializedName("createdAt")
        var createdAt: String?,
        @SerializedName("_id")
        var id: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("phone")
        var phone: String?,
        @SerializedName("postalCode")
        var postalCode: String?,
        @SerializedName("updatedAt")
        var updatedAt: String?,
        @SerializedName("userId")
        var userId: String?,
        @SerializedName("__v")
        var v: Int
    ) : Parcelable
}