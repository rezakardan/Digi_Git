package com.example.digikalax.data.db.basket

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digikalax.data.db.favorite.FavoriteDao
import com.example.digikalax.data.db.favorite.FavoriteEntity


@Database(
    entities = [CartEntity::class, FavoriteEntity::class],
    exportSchema = false,
    version = 69
)
abstract class CartDatabase() : RoomDatabase() {


    abstract fun cartDao(): CartDao

    abstract fun favoriteDao(): FavoriteDao


}