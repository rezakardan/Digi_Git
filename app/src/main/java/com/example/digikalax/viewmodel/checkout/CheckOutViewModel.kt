package com.example.digikalax.viewmodel.checkout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalax.data.models.confirm.BodyConfirmPurchase
import com.example.digikalax.data.models.confirm.ResponseConfirmPurchase
import com.example.digikalax.data.models.home.ResponsSuperMarketProducts
import com.example.digikalax.data.models.home.ResponseBestSellerProducts
import com.example.digikalax.data.models.home.ResponseCenterBanners
import com.example.digikalax.data.models.home.ResponseFavoriteProducts
import com.example.digikalax.data.models.home.ResponseGetAmazingProducts
import com.example.digikalax.data.models.home.ResponseGetBanners
import com.example.digikalax.data.models.home.ResponseGetCategories
import com.example.digikalax.data.models.home.ResponseMostDiscountedProducts
import com.example.digikalax.data.models.home.ResponseMostVisitedProducts
import com.example.digikalax.data.models.home.ResponseSliders
import com.example.digikalax.data.models.order.BodyResponseNewOrder
import com.example.digikalax.data.models.order.ResponseGetUserOrders
import com.example.digikalax.data.models.order.ResponseNewOrder
import com.example.digikalax.data.models.shippingCost.ResponseShippingCost
import com.example.digikalax.data.repositoryy.ceckout.CheckOutRepository
import com.example.digikalax.data.repositoryy.home.HomeRepository
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel@Inject constructor(private val repo: CheckOutRepository):ViewModel() {




    val getShippingCostLiveData=MutableLiveData<NetworkRequest<ResponseShippingCost>>()

    fun getShippingCostApi(address:String)=viewModelScope.launch {


        getShippingCostLiveData.value=NetworkRequest.Loading()

        val response=repo.getShippingCost(address)

        getShippingCostLiveData.value=NetworkResponse(response).generateResponse()


    }




    val newOrderLiveData=MutableLiveData<NetworkRequest<ResponseNewOrder>>()

    fun newOrderApi(body:BodyResponseNewOrder)=viewModelScope.launch {


        newOrderLiveData.value=NetworkRequest.Loading()

        val response=repo.newOrder(body)

        newOrderLiveData.value=NetworkResponse(response).generateResponse()



}




    val confirmLiveData=MutableLiveData<NetworkRequest<ResponseConfirmPurchase>>()

    fun confirmApi(body:BodyConfirmPurchase)=viewModelScope.launch {


        confirmLiveData.value=NetworkRequest.Loading()

        val response=repo.confirm(body)

        confirmLiveData.value=NetworkResponse(response).generateResponse()



    }







    val getUserOrdersLiveData=MutableLiveData<NetworkRequest<ResponseGetUserOrders>>()

    fun getUserOrders(token:String)=viewModelScope.launch {


        getUserOrdersLiveData.value=NetworkRequest.Loading()

        val response=repo.getUserOrders(token)

        getUserOrdersLiveData.value=NetworkResponse(response).generateResponse()



    }
}



