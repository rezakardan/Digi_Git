package com.example.digikalax.viewmodel.addresses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalax.data.models.addresses.BodyItemSaveAddress
import com.example.digikalax.data.models.addresses.BodyResponseSaveUserAddress
import com.example.digikalax.data.models.addresses.ResponseGetUserAddress
import com.example.digikalax.data.models.addresses.ResponseSaveUserAddress
import com.example.digikalax.data.repositoryy.addresses.AddressesRepository
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressesViewModel@Inject constructor(private val repo: AddressesRepository):ViewModel() {

val getUserAddressessLiveData=MutableLiveData<NetworkRequest<ResponseGetUserAddress>>()

    fun callGetUserAddresses(token:String)=viewModelScope.launch {

        getUserAddressessLiveData.value=NetworkRequest.Loading()

        val response=repo.getUserAddresses(token)

        getUserAddressessLiveData.value=NetworkResponse(response).generateResponse()



    }


    val saveUserAddressessLiveData=MutableLiveData<NetworkRequest<ResponseSaveUserAddress>>()

    fun callSaveUserAddresses(body:BodyResponseSaveUserAddress)=viewModelScope.launch {

        saveUserAddressessLiveData.value=NetworkRequest.Loading()

        val response=repo.saveUserAddresses(body)

        saveUserAddressessLiveData.value=NetworkResponse(response).generateResponse()



    }



    val itemSaveAddressLiveData=MutableLiveData<BodyItemSaveAddress>()
    fun getItemsSaveAddress(body: BodyItemSaveAddress){


        itemSaveAddressLiveData.value=body






    }


}



