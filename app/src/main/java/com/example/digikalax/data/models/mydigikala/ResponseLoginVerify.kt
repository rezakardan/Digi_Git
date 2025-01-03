package com.example.digikalax.data.models.mydigikala

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class ResponseLoginVerify(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("success")
    var success: Boolean
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("id")
        var id: String,
        @SerializedName("phone")
        var phone: String,
        @SerializedName("role")
        var role: String,
        @SerializedName("token")
        var token: String
    ) : Parcelable
}