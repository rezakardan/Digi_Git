package com.example.digikalax.data.repositoryy.addresses

import com.example.digikalax.data.models.addresses.BodyResponseSaveUserAddress
import com.example.digikalax.data.networkk.ApiService
import retrofit2.http.Body
import javax.inject.Inject

class AddressesRepository@Inject constructor(private val api: ApiService) {





  suspend  fun getUserAddresses(token:String)=api.getUserAddresses(token)


  suspend  fun saveUserAddresses(@Body body: BodyResponseSaveUserAddress)=api.saveUserAddress(body)

}