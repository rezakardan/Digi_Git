package com.example.digikalax.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.digikalax.utils.constants.Constants.SESSION_AUTH_DATA
import com.example.digikalax.utils.constants.Constants.USER_ID_DATA
import com.example.digikalax.utils.constants.Constants.USER_NAME_DATA
import com.example.digikalax.utils.constants.Constants.USER_PASSWORD_DATA
import com.example.digikalax.utils.constants.Constants.USER_PHONE_DATA
import com.example.digikalax.utils.constants.Constants.USER_TOKEN_DATA
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionManager@Inject constructor(@ApplicationContext private val context: Context) {



    companion object{


        private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(SESSION_AUTH_DATA)

        private val USER_TOKEN= stringPreferencesKey(USER_TOKEN_DATA)

        private val USER_PHONE = stringPreferencesKey(USER_PHONE_DATA)
        private val USER_ID= stringPreferencesKey(USER_ID_DATA)
        private val USER_NAME= stringPreferencesKey(USER_NAME_DATA)
        private val USER_PASSWORD= stringPreferencesKey(USER_PASSWORD_DATA)



    }




    suspend fun saveToken(token:String)=context.dataStore.edit {

        it[USER_TOKEN]=token




    }



    val getUserToken:Flow<String?> = context.dataStore.data.map {


        it[USER_TOKEN]




    }





    suspend fun savePhoneNumber(phoneNumber:String)= context.dataStore.edit {


        it[USER_PHONE]=phoneNumber




    }


    val getUserPhone:Flow<String?> = context.dataStore.data.map {


        it[USER_PHONE]


    }



  suspend  fun saveUserId(userId:String)= context.dataStore.edit {


        it[USER_ID]=userId




    }


    val getUserId:Flow<String?> = context.dataStore.data.map {


        it[USER_ID]


    }





   suspend fun saveUserName(userName:String)= context.dataStore.edit {


        it[USER_NAME]=userName




    }


    val getUserName:Flow<String?> = context.dataStore.data.map {


        it[USER_NAME]


    }



    suspend fun saveUserPassword(userPassword:String)= context.dataStore.edit {


        it[USER_PASSWORD]=userPassword




    }


    val getUserPassword:Flow<String?> = context.dataStore.data.map {


        it[USER_PASSWORD]


    }



}