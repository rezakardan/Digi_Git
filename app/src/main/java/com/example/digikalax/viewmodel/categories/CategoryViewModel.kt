package com.example.digikalax.viewmodel.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalax.data.models.categories.ResponseGetProductByCategory
import com.example.digikalax.data.models.categories.ResponseGetProductBySubCategory
import com.example.digikalax.data.models.categories.ResponseSubCategories
import com.example.digikalax.data.repositoryy.categories.CategoryRepository
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel@Inject constructor(private val repository: CategoryRepository):ViewModel() {


    val subCategoryLiveData=MutableLiveData<NetworkRequest<ResponseSubCategories>>()

    fun callSubCategory()=viewModelScope.launch {


        subCategoryLiveData.value=NetworkRequest.Loading()


        val response=repository.callSubCategories()

        subCategoryLiveData.value=NetworkResponse(response).generateResponse()





    }


    val getProductByCategoryLiveData=MutableLiveData<NetworkRequest<ResponseGetProductByCategory>>()

    fun getProductByCategory(categoryName: String,pageSize: String?="30",pageNumber: String?="1")=viewModelScope.launch {


        getProductByCategoryLiveData.value=NetworkRequest.Loading()


        val response=repository.getProductByCategory(categoryName, pageSize, pageNumber)

        getProductByCategoryLiveData.value=NetworkResponse(response).generateResponse()





    }




    val getProductBySubCategoryLiveData=MutableLiveData<NetworkRequest<ResponseGetProductBySubCategory>>()

    fun getProductBySubCategory(subCategoryId: String,pageSize: String?="30",pageNumber: String?="1")=viewModelScope.launch {


        getProductBySubCategoryLiveData.value=NetworkRequest.Loading()


        val response=repository.getProductBySubCategory(subCategoryId, pageSize, pageNumber)

        getProductBySubCategoryLiveData.value=NetworkResponse(response).generateResponse()





    }









}