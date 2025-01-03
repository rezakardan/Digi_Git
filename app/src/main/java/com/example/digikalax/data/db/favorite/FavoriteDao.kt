package com.example.digikalax.data.db.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikalax.data.models.comment.BodyResponseSetNewComment
import com.example.digikalax.utils.constants.Constants.FAVORITE_LIST_TABLE
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {


    @Query("SELECT*FROM FAVORITE_LIST_TABLE ")
    fun getAllFavoriteItems(): Flow<List<FavoriteEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteItem(favoriteEntity: FavoriteEntity)


    @Delete
    suspend fun deleteFavoriteItem(favoriteEntity: FavoriteEntity)



    @Query("SELECT EXISTS (SELECT 1 FROM $FAVORITE_LIST_TABLE  WHERE id = :id)")
     fun isExistInDatabase(id:String):Flow<Boolean>


}