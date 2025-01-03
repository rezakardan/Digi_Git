package com.example.digikalax.utils.network

import com.example.digikalax.data.repositoryy.ErrorResponse
import com.google.gson.Gson


open class NetworkResponse<T>(private val response:retrofit2.Response<T>) {



    open fun generateResponse():NetworkRequest<T>{

        return when {
            response.code() == 401 -> NetworkRequest.Error("You are not authorized")
            response.code() == 422 -> {
                var errorMessage = ""
                if (response.errorBody() != null) {
                    val errorResponse = Gson().fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java)
                    //val message = errorResponse.message
                    val errors = errorResponse.errors
                    errors?.forEach { (_, fieldError) ->
                        errorMessage = fieldError.joinToString()
                    }
                }
                NetworkRequest.Error(errorMessage)
            }

            response.code() == 500 -> NetworkRequest.Error("Try again")
            response.isSuccessful -> NetworkRequest.Success(response.body()!!)
            else -> NetworkRequest.Error(response.message())
        }







    }
}