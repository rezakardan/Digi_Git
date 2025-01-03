package com.example.digikalax.data.repositoryy.mydigikala

import com.example.digikalax.data.db.basket.CartDao
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.data.db.basket.CartStatus
import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.data.models.setname.BodyResponseSetName
import com.example.digikalax.data.networkk.ApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import javax.inject.Inject

class MyDigiKalaRepository @Inject constructor(
private val api:ApiService
) {


    suspend fun loginAndVerify(body:BodyResponseLogin)=api.loginAndVerify(body)


    suspend fun setNameApi( body: BodyResponseSetName)=api.setNameApi(body)



}
