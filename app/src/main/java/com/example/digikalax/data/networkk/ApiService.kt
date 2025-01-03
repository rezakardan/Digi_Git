package com.example.digikalax.data.networkk

import com.example.digikalax.data.models.addresses.BodyResponseSaveUserAddress
import com.example.digikalax.data.models.addresses.ResponseGetUserAddress
import com.example.digikalax.data.models.addresses.ResponseSaveUserAddress
import com.example.digikalax.data.models.basket.ResponseSugestion
import com.example.digikalax.data.models.categories.ResponseGetProductByCategory
import com.example.digikalax.data.models.categories.ResponseGetProductBySubCategory
import com.example.digikalax.data.models.categories.ResponseSubCategories
import com.example.digikalax.data.models.comment.BodyResponseSetNewComment
import com.example.digikalax.data.models.comment.ResponseGetAllComments
import com.example.digikalax.data.models.comment.ResponseGetUserComments
import com.example.digikalax.data.models.comment.ResponseSetNewComment
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
import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.data.models.mydigikala.ResponseLoginVerify
import com.example.digikalax.data.models.order.BodyResponseNewOrder
import com.example.digikalax.data.models.order.ResponseGetUserOrders
import com.example.digikalax.data.models.order.ResponseNewOrder
import com.example.digikalax.data.models.product_detail.ResponseGetProductById
import com.example.digikalax.data.models.product_detail.ResponseGetSimilarProducts
import com.example.digikalax.data.models.setname.BodyResponseSetName
import com.example.digikalax.data.models.setname.ResponseSetName
import com.example.digikalax.data.models.shippingCost.ResponseShippingCost
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    @GET("v1/getSlider")
    suspend fun callGetSlider(): Response<ResponseSliders>


    @GET("v1/getAmazingproducts")
    suspend fun callAmazingProductApi(): Response<ResponseGetAmazingProducts>


    @GET("v1/getSuperMarketAmazingProducts")
    suspend fun callSuperMarketProductApi(): Response<ResponsSuperMarketProducts>


    @GET("v1/get4Banners")
    suspend fun callGetBanners(): Response<ResponseGetBanners>

    @GET("v1/getCategories")
    suspend fun callGetCategories(): Response<ResponseGetCategories>


    @GET("v1/getCenterBanners")
    suspend fun callGetCenterBanners(): Response<ResponseCenterBanners>


    @GET("v1/getBestSellerProducts")
    suspend fun callBestSellerProducts(): Response<ResponseBestSellerProducts>


    @GET("v1/getMostVisitedProducts")
    suspend fun callMostVisitedProducts(): Response<ResponseMostVisitedProducts>


    @GET("v1/getMostFavoriteProducts")
    suspend fun callFavoriteProducts(): Response<ResponseFavoriteProducts>


    @GET("v1/getMostDiscountedProducts")
    suspend fun callMostDiscountedProducts(): Response<ResponseMostDiscountedProducts>


    @GET("v1/getSubCategories")
    suspend fun callGetSubCategories(): Response<ResponseSubCategories>

    @GET("v1/getAllProducts")
    suspend fun callSugestionForYou(): Response<ResponseSugestion>


    @POST("v1/login")
    suspend fun loginAndVerify(@Body body: BodyResponseLogin): Response<ResponseLoginVerify>


    @GET("v1/getUserAddress")
    suspend fun getUserAddresses(@Query("token") token: String): Response<ResponseGetUserAddress>

    @POST("v1/saveUserAddress")
    suspend fun saveUserAddress(@Body body: BodyResponseSaveUserAddress): Response<ResponseSaveUserAddress>

    @GET("v1/getShippingCost")
    suspend fun getShippingCost(@Query("address") address: String): Response<ResponseShippingCost>


    @POST("v1/setNewOrder")
    suspend fun newOrder(@Body body: BodyResponseNewOrder): Response<ResponseNewOrder>


    @POST("v1/confirmPurchase")
    suspend fun confirmPurchase(@Body body: BodyConfirmPurchase): Response<ResponseConfirmPurchase>        //orderid     67374b14148d92dd20dbd506


    @GET("v1/getProductById")
    suspend fun getProductById(@Query("id") id: String): Response<ResponseGetProductById>


    @GET("v1/getSimilarProducts")
    suspend fun getSimilarProducts(@Query("categoryId") categoryId: String): Response<ResponseGetSimilarProducts>


    @POST("v1/setNewComment")
    suspend fun productsSetNewComment(@Body body: BodyResponseSetNewComment): Response<ResponseSetNewComment>

    @GET("v1/getAllProductComments")
    suspend fun productsGetAllComment(
        @Query("id")              id: String,
         @Query("pageSize")      pageSize: String?="80",
        @Query("pageNumber")      pageNumber: String?="1"
    ): Response<ResponseGetAllComments>


    @POST("v1/setUserName")
    suspend fun setNameApi(@Body body: BodyResponseSetName): Response<ResponseSetName>


    @GET("v1/getUserComments")
    suspend fun getUserComments(


        @Query("token") token: String,
        @Query("pageSize") pageSize: String? = "80",
        @Query("pageNumber") pageNumber: String? = "1"
    ): Response<ResponseGetUserComments>


    @GET("v1/getUserOrders")
    suspend fun getUserOrders(@Query("token") token: String): Response<ResponseGetUserOrders>


    @GET("v1/getProductByCategory")
    suspend fun getProductByCategory(
        @Query("categoryName") categoryName: String,
        @Query("pageSize") pageSize: String? = "30",
        @Query("pageNumber") pageNumber: String? = "1"
    ): Response<ResponseGetProductByCategory>




    @GET("v1/getProductBySubCategory")
    suspend fun getProductBySubCategory(
        @Query("subCategoryId") subCategoryId: String,
        @Query("pageSize") pageSize: String? = "30",
        @Query("pageNumber") pageNumber: String? = "1"
    ): Response<ResponseGetProductBySubCategory>




}