package com.example.digikalax.utils.di

import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.data.models.order.BodyResponseNewOrder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class BodyModule {



    @Provides
    @Singleton
    fun provideBodyLogin()=BodyResponseLogin()


  //  @Provides
 //   @Singleton
  //  fun provideNewOrder()=BodyResponseNewOrder()


}