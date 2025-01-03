package com.example.digikalax.data.repositoryy.getallcomments

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.digikalax.data.networkk.ApiService
import javax.inject.Inject
import kotlin.math.max

class GetAllCommentsRepository@Inject constructor(private val api:ApiService) {










    suspend fun productsGetAllComment( id: String,
                                       pageSize: String?="80",
                                       pageNumber: String?="1")=api.productsGetAllComment(id, pageSize, pageNumber)


   suspend fun getUserComments(token:String,pageSize: String?="80",
                        pageNumber: String?="1")=api.getUserComments(token,pageSize,pageNumber)








}