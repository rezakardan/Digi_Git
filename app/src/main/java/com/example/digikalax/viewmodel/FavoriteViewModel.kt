package com.example.digikalax.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalax.data.db.favorite.FavoriteEntity
import com.example.digikalax.data.repositoryy.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel@Inject constructor(private val repository: FavoriteRepository):ViewModel() {





val isProductInDatabaseLiveData=MutableLiveData<Boolean>()
   suspend fun existInDatabase(id:String)= viewModelScope.launch{



       repository.isFavoriteItemInDatabase(id) .collect{




               isProductInDatabaseLiveData.postValue(it)




       }






   }














val loadAllFavoriteLiveData=MutableLiveData<List<FavoriteEntity>>()
    fun loadAllFavoriteItems()=viewModelScope.launch {


        repository.getAllFavoriteItems().collect{

loadAllFavoriteLiveData.value=it



        }



    }




    fun deleteFavoriteItems(favoriteEntity: FavoriteEntity)=viewModelScope.launch {


        repository.deleteFavoriteItem(favoriteEntity)



    }



    fun saveFavoriteItems(favoriteEntity: FavoriteEntity)=viewModelScope.launch {


        repository.addFavoriteItem(favoriteEntity)



    }


}