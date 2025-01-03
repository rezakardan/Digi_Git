package com.example.digikalax.data.repositoryy.categories

import com.example.digikalax.data.networkk.ApiService
import javax.inject.Inject

class CategoryRepository@Inject constructor(private val api: ApiService) {


    suspend fun callSubCategories()=api.callGetSubCategories()

    suspend fun getProductByCategory(categoryName: String,pageSize: String?="30",pageNumber: String?="1")=api.getProductByCategory(categoryName, pageSize, pageNumber)

    suspend fun getProductBySubCategory(subCategoryId: String,pageSize: String?="30",pageNumber: String?="1")=api.getProductBySubCategory(subCategoryId, pageSize, pageNumber)









}