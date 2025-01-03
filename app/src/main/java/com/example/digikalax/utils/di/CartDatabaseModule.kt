package com.example.digikalax.utils.di

import android.content.Context
import androidx.room.Room
import com.example.digikalax.data.db.basket.CartDatabase
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.utils.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CartDatabaseModule {


    @Provides
    @Singleton
    fun provideDao(db: CartDatabase) = db.cartDao()



    @Provides
    @Singleton
    fun provideFavoriteDao(db: CartDatabase) = db.favoriteDao()










    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CartDatabase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()






}