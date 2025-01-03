package com.example.digikalax.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
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
import com.example.digikalax.data.repositoryy.home.HomeRepository
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val repo: HomeRepository):ViewModel() {


val getSliderLiveData=MutableLiveData<NetworkRequest<ResponseSliders>>()


    fun callSliderApi()=viewModelScope.launch {

        getSliderLiveData.value=NetworkRequest.Loading()


        val response=repo.getSliderApi()

        getSliderLiveData.value=NetworkResponse(response).generateResponse()






    }




    val amazingProductLiveData=MutableLiveData<NetworkRequest<ResponseGetAmazingProducts>>()

fun callAmazingProduct()=viewModelScope.launch {


    amazingProductLiveData.value=NetworkRequest.Loading()


    val response=repo.getAmazingProducts()

    amazingProductLiveData.value=NetworkResponse(response).generateResponse()




}



    val superMarketProductLiveData=MutableLiveData<NetworkRequest<ResponsSuperMarketProducts>>()

    fun callSuperMarketProduct()=viewModelScope.launch {


        superMarketProductLiveData.value=NetworkRequest.Loading()


        val response=repo.getSuperMarketProduct()

        superMarketProductLiveData.value=NetworkResponse(response).generateResponse()




    }




    val getBannersLiveData=MutableLiveData<NetworkRequest<ResponseGetBanners>>()

    fun callGetBanners()=viewModelScope.launch {


        getBannersLiveData.value=NetworkRequest.Loading()


        val response=repo.getBanners()

        getBannersLiveData.value=NetworkResponse(response).generateResponse()




    }



    val categoryLiveData=MutableLiveData<NetworkRequest<ResponseGetCategories>>()

    fun callCategory()=viewModelScope.launch {


        categoryLiveData.value=NetworkRequest.Loading()


        val response=repo.getCategories()

        categoryLiveData.value=NetworkResponse(response).generateResponse()




    }



    val centerBannerLiveData=MutableLiveData<NetworkRequest<ResponseCenterBanners>>()

    fun callCenterBanners()=viewModelScope.launch {


        centerBannerLiveData.value=NetworkRequest.Loading()


        val response=repo.getCenterBanners()

        centerBannerLiveData.value=NetworkResponse(response).generateResponse()




    }


    val bestSellerLiveData=MutableLiveData<NetworkRequest<ResponseBestSellerProducts>>()

    fun callBestSeller()=viewModelScope.launch {


        bestSellerLiveData.value=NetworkRequest.Loading()


        val response=repo.getBestSellerProducts()

        bestSellerLiveData.value=NetworkResponse(response).generateResponse()




    }



    val mostVisitedProductLiveData=MutableLiveData<NetworkRequest<ResponseMostVisitedProducts>>()

    fun callMostVisitedProducts()=viewModelScope.launch {


        mostVisitedProductLiveData.value=NetworkRequest.Loading()


        val response=repo.getMostVisitedProducts()

        mostVisitedProductLiveData.value=NetworkResponse(response).generateResponse()




    }



    val favoriteProductLiveData=MutableLiveData<NetworkRequest<ResponseFavoriteProducts>>()

    fun callFavoriteProducts()=viewModelScope.launch {


        favoriteProductLiveData.value=NetworkRequest.Loading()


        val response=repo.getFavoriteProducts()

        favoriteProductLiveData.value=NetworkResponse(response).generateResponse()




    }



    val mostDicountedProductLiveData=MutableLiveData<NetworkRequest<ResponseMostDiscountedProducts>>()

    fun callMostDiscountedProducts()=viewModelScope.launch {


        mostDicountedProductLiveData.value=NetworkRequest.Loading()


        val response=repo.getMostDiscountedProducts()

        mostDicountedProductLiveData.value=NetworkResponse(response).generateResponse()




    }










}