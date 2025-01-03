package com.example.digikalax.data.models.confirm

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class ResponseConfirmPurchase(
    @SerializedName("data")
    var `data`: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("success")
    var success: Boolean
) : Parcelable