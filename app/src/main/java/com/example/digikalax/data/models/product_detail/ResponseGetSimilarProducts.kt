package com.example.digikalax.data.models.product_detail


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ResponseGetSimilarProducts(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: String,
    @SerializedName("success")
    var success: Boolean
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("category")
        var category: String,
        @SerializedName("discountPercent")
        var discountPercent: Int,
        @SerializedName("_id")
        var id: String,
        @SerializedName("image")
        var image: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("price")
        var price: Int,
        @SerializedName("seller")
        var seller: String,
        @SerializedName("star")
        var star: Double
    ) : Parcelable
}