package com.example.digikalax.data.repositoryy.home

import com.example.digikalax.data.networkk.ApiService
import javax.inject.Inject

class HomeRepository@Inject constructor(private val api: ApiService) {

suspend fun getSliderApi()=api.callGetSlider()


    suspend fun getAmazingProducts()=api.callAmazingProductApi()

suspend fun getSuperMarketProduct()=api.callSuperMarketProductApi()


    suspend fun getBanners()=api.callGetBanners()


    suspend fun getCategories()=api.callGetCategories()

    suspend fun getCenterBanners()=api.callGetCenterBanners()


    suspend fun getBestSellerProducts()=api.callBestSellerProducts()
    suspend fun getMostVisitedProducts()=api.callMostVisitedProducts()


    suspend fun getFavoriteProducts()=api.callFavoriteProducts()

    suspend fun getMostDiscountedProducts()=api.callMostDiscountedProducts()



}