package com.example.digikalax.data.models.setname


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class BodyResponseSetName(
    @SerializedName("name")
    var name: String,
    @SerializedName("token")
    var token: String
) : Parcelable