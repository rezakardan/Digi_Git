package com.example.digikalax.data.models.confirm


data class BodyConfirmPurchase(
    var orderId: String,
    var token: String,
    var transactionId: String
)