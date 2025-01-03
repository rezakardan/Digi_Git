package com.example.digikalax.data.models.shippingCost

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class ResponseShippingCost(

    @SerializedName("data")
    var `data`: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("success")
    var success: Boolean
)  : Parcelable