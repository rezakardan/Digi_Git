package com.example.digikalax.viewmodel.product_detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalax.data.models.comment.BodyResponseSetNewComment
import com.example.digikalax.data.models.comment.ResponseGetAllComments
import com.example.digikalax.data.models.comment.ResponseSetNewComment
import com.example.digikalax.data.models.product_detail.ResponseGetProductById
import com.example.digikalax.data.models.product_detail.ResponseGetSimilarProducts
import com.example.digikalax.data.repositoryy.product_details.ProductDetailRepository
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.utils.network.NetworkResponse
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repository: ProductDetailRepository) :
    ViewModel() {




        val productIdLiveData=MutableLiveData<NetworkRequest<ResponseGetProductById>>()

    fun getProductIdApi(id:String)=viewModelScope.launch {



        productIdLiveData.value=NetworkRequest.Loading()


        val response=repository.getProductById(id)

        productIdLiveData.value=NetworkResponse(response).generateResponse()



    }





    val getSimilarProductsLiveData=MutableLiveData<NetworkRequest<ResponseGetSimilarProducts>>()

    fun getSimilarProductsApi(categoryId:String)=viewModelScope.launch {



        getSimilarProductsLiveData.value=NetworkRequest.Loading()


        val response=repository.getSimilarProducts(categoryId)

        getSimilarProductsLiveData.value=NetworkResponse(response).generateResponse()



    }





    val productNewCommentLiveData=MutableLiveData<NetworkRequest<ResponseSetNewComment>>()

    fun setNewComment(body:BodyResponseSetNewComment)=viewModelScope.launch {



        productNewCommentLiveData.value=NetworkRequest.Loading()


        val response=repository.setNewComment(body)

        productNewCommentLiveData.value=NetworkResponse(response).generateResponse()



    }




}