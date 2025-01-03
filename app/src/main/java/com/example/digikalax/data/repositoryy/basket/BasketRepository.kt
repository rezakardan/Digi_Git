package com.example.digikalax.data.repositoryy.basket

import androidx.lifecycle.asLiveData
import com.example.digikalax.data.db.basket.CartDao
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.data.db.basket.CartStatus
import com.example.digikalax.data.networkk.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val api: ApiService,
private val dao: CartDao
) {


    suspend fun callSugestionForYou() = api.callSugestionForYou()


   suspend fun addToCart(entity: CartEntity) = dao.addToCart(entity)





    suspend fun deleteProductFromDatabase(entity: CartEntity)=dao.deleteProduct(entity)


    fun deleteAllProducts()=dao.deleteAllProducts(CartStatus.CURRENT_CART)






    val currentCatItems = dao.getAllProducts(CartStatus.CURRENT_CART)



    suspend fun updateCount(newCount:Int, myName:String)=dao.updateCount(newCount, myName)


    suspend fun changeStatus(newStatus: CartStatus,myName:String)=dao.changeStatus(newStatus, myName)






    val nextCartItem=dao.getAllProducts(CartStatus.NEXT_CART)


    val getItemCountCurrentCart=dao.getCartItemCount(CartStatus.CURRENT_CART)



    val getItemCountNextCart=dao.getCartItemCount(CartStatus.NEXT_CART)








    val getAllProductPriceCurrentCart=dao.getAllProductsPrice(CartStatus.CURRENT_CART)




    val getAllProductPriceNextCart=dao.getAllProductsPrice(CartStatus.NEXT_CART)



    fun   isProductInBasketDb(id:String)=dao.isProductInBasketDb(id)





}
