package com.example.digikalax.data.repositoryy.product_details

import com.example.digikalax.data.models.comment.BodyResponseSetNewComment
import com.example.digikalax.data.networkk.ApiService
import javax.inject.Inject

class ProductDetailRepository@Inject constructor(private val api: ApiService) {





suspend fun getProductById(id:String)=api.getProductById(id)


    suspend fun getSimilarProducts(categoryId:String)=api.getSimilarProducts(categoryId)





    suspend fun setNewComment(body:BodyResponseSetNewComment)=api.productsSetNewComment(body)

 //   suspend fun getAllComment(id:String,pageSize:Int,pageNumber:Int)=api.productsGetAllComment(id, pageSize, pageNumber)

}