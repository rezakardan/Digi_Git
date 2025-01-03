package com.example.digikalax.viewmodel.allcomments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalax.data.models.comment.ResponseGetAllComments
import com.example.digikalax.data.models.comment.ResponseGetUserComments
import com.example.digikalax.data.models.product_detail.ResponseGetProductById
import com.example.digikalax.data.repositoryy.getallcomments.GetAllCommentsRepository
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllCommentsViewModel @Inject constructor(private val repository: GetAllCommentsRepository) :
    ViewModel() {





    val productsGetAllCommentLiveData= MutableLiveData<NetworkRequest<ResponseGetAllComments>>()

    fun productsGetAllComment(id:String)=viewModelScope.launch {



        productsGetAllCommentLiveData.value= NetworkRequest.Loading()


        val response=repository.productsGetAllComment(id)

        productsGetAllCommentLiveData.value= NetworkResponse(response).generateResponse()



    }







    val getUserCommentsLiveData=MutableLiveData<NetworkRequest<ResponseGetUserComments>>()

    fun getUserComments(id:String)=viewModelScope.launch {



        getUserCommentsLiveData.value=NetworkRequest.Loading()


        val response=repository.getUserComments(id)

        getUserCommentsLiveData.value=NetworkResponse(response).generateResponse()



    }


}