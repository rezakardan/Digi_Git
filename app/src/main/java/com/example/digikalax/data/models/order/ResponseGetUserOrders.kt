package com.example.digikalax.data.models.order


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ResponseGetUserOrders(
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
        @SerializedName("_id")
        var id: String,
        @SerializedName("orderAddress")
        var orderAddress: String,
        @SerializedName("orderProducts")
        var orderProducts: List<OrderProduct>,
        @SerializedName("orderStatus")
        var orderStatus: String,
        @SerializedName("orderTotalDiscount")
        var orderTotalDiscount: Int,
        @SerializedName("orderTotalPrice")
        var orderTotalPrice: Int,
        @SerializedName("orderUserName")
        var orderUserName: String,
        @SerializedName("orderUserPhone")
        var orderUserPhone: String,
        @SerializedName("transactionId")
        var transactionId: String,
        @SerializedName("updatedAt")
        var updatedAt: String,
        @SerializedName("userId")
        var userId: String,
        @SerializedName("__v")
        var v: Int
    ) : Parcelable {
        @Parcelize
        data class OrderProduct(
            @SerializedName("count")
            var count: Int,
            @SerializedName("discountPercent")
            var discountPercent: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("itemId")
            var itemId: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("price")
            var price: Int,
            @SerializedName("seller")
            var seller: String
        ) : Parcelable
    }
}