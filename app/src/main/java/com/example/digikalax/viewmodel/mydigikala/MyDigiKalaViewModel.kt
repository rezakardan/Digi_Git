package com.example.digikalax.viewmodel.mydigikala

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.data.models.mydigikala.ResponseLoginVerify
import com.example.digikalax.data.models.setname.BodyResponseSetName
import com.example.digikalax.data.models.setname.ResponseSetName
import com.example.digikalax.data.repositoryy.mydigikala.MyDigiKalaRepository
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyDigiKalaViewModel @Inject constructor(private val repository: MyDigiKalaRepository): ViewModel() {









    val loginAndVerifyLiveData=MutableLiveData<NetworkRequest<ResponseLoginVerify>>()

    fun callLoginVerify(body:BodyResponseLogin)=viewModelScope.launch {


        loginAndVerifyLiveData.value=NetworkRequest.Loading()

        val response=repository.loginAndVerify(body)

        loginAndVerifyLiveData.value=NetworkResponse(response).generateResponse()


    }




    val setNameLiveData=MutableLiveData<NetworkRequest<ResponseSetName>>()

    fun callSetName(body:BodyResponseSetName)=viewModelScope.launch {


        setNameLiveData.value=NetworkRequest.Loading()

        val response=repository.setNameApi(body)

        setNameLiveData.value=NetworkResponse(response).generateResponse()


    }








}