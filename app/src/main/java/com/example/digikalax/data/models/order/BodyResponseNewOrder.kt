package com.example.digikalax.data.models.order

import com.example.digikalax.data.db.basket.CartEntity


data class BodyResponseNewOrder(
    var token: String?,
    var orderTotalPrice: Long,
    var orderTotalDiscount: Long,

    var orderAddress: String,
    var orderUserName: String,

    var orderUserPhone: String,

    var orderProducts: List<CartEntity>,

)