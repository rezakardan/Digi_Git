package com.example.digikalax.viewmodel.basket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.data.db.basket.CartStatus
import com.example.digikalax.data.models.basket.CartDetails
import com.example.digikalax.data.models.basket.ResponseSugestion
import com.example.digikalax.data.repositoryy.basket.BasketRepository
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val repository: BasketRepository) : ViewModel() {





//    init {
//
//        viewModelScope.launch {
//            repository.currentCatItems.collectLatest {
//
//
//                calculateDetailCart(it)
//
//                currentCArtItemsInCheckFragment.emit(it)
//
//            }
//        }
//
//
//    }



//    val cartDetail = MutableLiveData(CartDetails())
//
//
//    private fun calculateDetailCart(item: List<CartEntity>) {
//
//
//        var totalPrice = 0L
//        var totalDiscount = 0L
//        var payAblePrice = 0L
//        var totalCount = 0
//        var countFromOneProduct = 0
//        item.forEach { items ->
//
//
//            totalPrice += ((items.price!!).times(items.count!!))
//
//
//
//            if (items.discountPercent!!.toInt() >0) {
//
//
//                totalDiscount += (((items.price!!.toLong()) * (items.discountPercent!!.toLong())) / 100)
//
//
//            } else {
//
//                totalDiscount += 0
//
//
//            }
//
//            totalCount += items.count!!
//            countFromOneProduct++
//
//        }
//
//        payAblePrice = totalPrice - totalDiscount
//
//
//
//        cartDetail.value = CartDetails(
//            totalCount,
//            totalPrice,
//            totalDiscount.toInt(),
//            payAblePrice,
//            countFromOneProduct
//        )
//
//        Log.e("cartDetail","${cartDetail.value}")
//    }


    val currentCArtItemsInCheckFragment: MutableStateFlow<List<CartEntity>> = MutableStateFlow(emptyList())


    val getCurrentCartItems= repository.currentCatItems.asLiveData()





    val sugestionLiveData = MutableLiveData<NetworkRequest<ResponseSugestion>>()

    fun callSugestion() = viewModelScope.launch {


        sugestionLiveData.value = NetworkRequest.Loading()

        val response = repository.callSugestionForYou()

        sugestionLiveData.value = NetworkResponse(response).generateResponse()


    }


    fun addToCart(entity: CartEntity) = viewModelScope.launch {

        repository.addToCart(entity)

    }


    fun deleteProductFromDatabase(entity: CartEntity) = viewModelScope.launch {

        repository.deleteProductFromDatabase(entity)

    }


    fun updateCounts(count: Int, myName: String) = viewModelScope.launch {


        repository.updateCount(count, myName)


    }


    fun changeStatus(newStatus: CartStatus, myName: String) = viewModelScope.launch {


        repository.changeStatus(newStatus, myName)


    }








    val nextCartItem: Flow<List<CartEntity>> = repository.nextCartItem


    val getCurrentCartItemCount= repository.getItemCountCurrentCart.asLiveData()

    val getNextCartCount: Flow<Int> = repository.getItemCountNextCart


    val getAllProductPriceCurrentCart = repository.getAllProductPriceCurrentCart.asLiveData()

    val getAllProductPriceNextCart: Flow<Long> = repository.getAllProductPriceNextCart


    fun deleteAllProducts() = viewModelScope.launch {


        repository.deleteAllProducts()


    }










    val isProductInBasketDbLiveData=MutableLiveData<Boolean>()
    suspend fun isProductInBasketDb(id:String)= viewModelScope.launch{


        repository.isProductInBasketDb(id).collect{



            isProductInBasketDbLiveData.postValue(it)


        }




    }






}