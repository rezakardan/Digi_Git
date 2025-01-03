package com.example.digikalax.data.models.product_detail


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.JsonObject
import kotlinx.parcelize.RawValue

@Parcelize
data class ResponseGetProductById(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("success")
    var success: Boolean
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("agreeCount")
        var agreeCount: Int,
        @SerializedName("agreePercent")
        var agreePercent: Int,
        @SerializedName("category")
        var category: String,
        @SerializedName("categoryId")
        var categoryId: String,
        @SerializedName("colors")
        var colors: List<Color>,
        @SerializedName("commentCount")
        var commentCount: Int,
        @SerializedName("comments")
        var comments: List<Comment>,
        @SerializedName("description")
        var description: String,
        @SerializedName("discountPercent")
        var discountPercent: Int,
        @SerializedName("_id")
        var id: String,
        @SerializedName("imageSlider")
        var imageSlider: List<ImageSlider>,
        @SerializedName("name")
        var name: String,
        @SerializedName("price")
        var price: Long,
        @SerializedName("priceList")
        var priceList: List<Price>,
        @SerializedName("questionCount")
        var questionCount: Int,
        @SerializedName("seller")
        var seller: String,
        @SerializedName("star")
        var star: Double,
        @SerializedName("starCount")
        var starCount: Int,
        @SerializedName("technicalFeatures")
        var technicalFeatures:@RawValue JsonObject?=null,
        @SerializedName("viewCount")
        var viewCount: Int
    ) : Parcelable {
        @Parcelize
        data class Color(
            @SerializedName("code")
            var code: String,
            @SerializedName("color")
            var color: String,
            @SerializedName("_id")
            var id: String
        ) : Parcelable

        @Parcelize
        data class Comment(
            @SerializedName("createdAt")
            var createdAt: String,
            @SerializedName("description")
            var description: String,
            @SerializedName("_id")
            var id: String,
            @SerializedName("productId")
            var productId: String,
            @SerializedName("star")
            var star: Int,
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

        @Parcelize
        data class ImageSlider(
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("productId")
            var productId: String
        ) : Parcelable

        @Parcelize
        data class Price(
            @SerializedName("persianDate")
            var persianDate: String,
            @SerializedName("price")
            var price: Int
        ) : Parcelable

        @Parcelize
        data class TechnicalFeatures(
            @SerializedName("تعداد پورت")
            var تعدادپورت: String,
            @SerializedName("حداکثر ولتاژ")
            var حداکثرولتاژ: String,
            @SerializedName("طول سیم")
            var طولسیم: String,
            @SerializedName("مناسب برای")
            var مناسببرای: String,
            @SerializedName("کیفیت تصویر")
            var کیفیتتصویر: String
        ) : Parcelable
  }
}