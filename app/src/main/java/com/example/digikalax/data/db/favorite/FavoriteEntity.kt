package com.example.digikalax.data.db.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikalax.utils.constants.Constants.FAVORITE_LIST_TABLE


@Entity(tableName = FAVORITE_LIST_TABLE)
data class FavoriteEntity(

    @PrimaryKey
    var id:String,
    var discountPercent:Int?=null,

    var image:String?=null,




    var name:String?=null,



    var price:String?=null,
    var seller:String?=null,
    var star:Double?=null






    )
