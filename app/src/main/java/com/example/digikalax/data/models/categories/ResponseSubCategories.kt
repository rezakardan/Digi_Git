package com.example.digikalax.data.models.categories

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class ResponseSubCategories(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("success")
    var success: Boolean
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("beauty")
        var beauty: List<Beauty>,
        @SerializedName("book")
        var book: List<Book>,
        @SerializedName("digital")
        var digital: List<Digital>,
        @SerializedName("fashion")
        var fashion: List<Fashion>,
        @SerializedName("home")
        var home: List<Home>,
        @SerializedName("mobile")
        var mobile: List<Mobile>,
        @SerializedName("native")
        var native: List<Native>,
        @SerializedName("sport")
        var sport: List<Sport>,
        @SerializedName("supermarket")
        var supermarket: List<Supermarket>,
        @SerializedName("tool")
        var tool: List<Tool>,
        @SerializedName("toy")
        var toy: List<Toy>
    ) : Parcelable {
        @Parcelize
        data class Beauty(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Book(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Digital(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Fashion(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Home(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Mobile(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Native(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Sport(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Supermarket(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Tool(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable

        @Parcelize
        data class Toy(
            @SerializedName("count")
            var count: Int,
            @SerializedName("_id")
            var id: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String
        ) : Parcelable
    }
}