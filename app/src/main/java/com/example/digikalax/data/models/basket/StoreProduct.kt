package com.example.digikalax.data.models.basket

data class StoreProduct(
    val _id: String,
    val name: String,
    val seller: String,
    val price: Long,
    val discountPercent: Int,
    val image: String,
    val star: Double,
)
