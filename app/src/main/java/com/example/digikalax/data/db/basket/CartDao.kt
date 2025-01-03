package com.example.digikalax.data.db.basket

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikalax.utils.constants.Constants
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartEntity: CartEntity)


    @Query("SELECT * FROM ${Constants.SHOPPING_CART_TABLE} WHERE cartStatus=:status")
    fun getAllProducts(status: CartStatus): Flow<List<CartEntity>>


    @Delete
    suspend fun deleteProduct(entity: CartEntity)


    @Query("UPDATE SHOPPING_CART SET count=:newCount WHERE name=:myName")
    suspend fun updateCount(newCount: Int, myName: String)

    @Query("UPDATE SHOPPING_CART SET cartStatus=:newStatus WHERE name=:myName")
    suspend fun changeStatus(newStatus: CartStatus, myName: String)


    @Query("SELECT TOTAL(count) AS count FROM SHOPPING_CART WHERE cartStatus=:newStatus")
    fun getCartItemCount(newStatus: CartStatus): Flow<Int>




    @Query("SELECT SUM (COALESCE(price, 0) * COALESCE(count, 0) * (1 - (COALESCE(discountPercent, 0) / 100.0)))  FROM  SHOPPING_CART WHERE cartStatus=:newStatus")
    fun getAllProductsPrice(newStatus: CartStatus):Flow<Long>


    @Query("DELETE FROM SHOPPING_CART WHERE cartStatus=:status")
    fun deleteAllProducts(status: CartStatus)




    @Query("SELECT EXISTS (SELECT 1 FROM ${Constants.SHOPPING_CART_TABLE} WHERE itemId=:id)")
     fun isProductInBasketDb(id:String):Flow<Boolean>




}