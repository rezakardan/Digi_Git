package com.example.digikalax.data.repositoryy.ceckout

import com.example.digikalax.data.models.confirm.BodyConfirmPurchase
import com.example.digikalax.data.models.order.BodyResponseNewOrder
import com.example.digikalax.data.networkk.ApiService
import javax.inject.Inject

class CheckOutRepository@Inject constructor(private val api: ApiService) {

suspend fun getShippingCost(address:String)=api.getShippingCost(address)

    suspend fun newOrder(body:BodyResponseNewOrder)=api.newOrder(body)

suspend fun confirm(bodyConfirmPurchase: BodyConfirmPurchase)=api.confirmPurchase(bodyConfirmPurchase)



    suspend fun getUserOrders(token:String)=api.getUserOrders(token)




}