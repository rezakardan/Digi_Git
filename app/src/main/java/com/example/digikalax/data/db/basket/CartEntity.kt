package com.example.digikalax.data.db.basket

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikalax.utils.constants.Constants


@Entity(tableName = Constants.SHOPPING_CART_TABLE)
data class CartEntity(


    var itemId:String?="0",



    @PrimaryKey
    var name:String,


    var seller:String?="",

    var price:Long?=0,

    var discountPercent:Int?=0,

    var image:String?="",


    var count:Int?=0,







    var cartStatus:CartStatus?=CartStatus.CURRENT_CART





)
