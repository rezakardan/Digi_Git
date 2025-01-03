package com.example.digikalax.data.repositoryy

import com.example.digikalax.data.db.favorite.FavoriteDao
import com.example.digikalax.data.db.favorite.FavoriteEntity
import javax.inject.Inject

class FavoriteRepository@Inject constructor(private val dao: FavoriteDao) {


    fun getAllFavoriteItems()=dao.getAllFavoriteItems()


    suspend fun addFavoriteItem(favoriteEntity: FavoriteEntity)=dao.addFavoriteItem(favoriteEntity)


    suspend fun deleteFavoriteItem(favoriteEntity: FavoriteEntity)=dao.deleteFavoriteItem(favoriteEntity)



     fun isFavoriteItemInDatabase(id:String)=dao.isExistInDatabase(id)


}