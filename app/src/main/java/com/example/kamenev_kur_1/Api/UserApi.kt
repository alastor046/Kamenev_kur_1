package com.example.kamenev_kur_1.Api

import com.example.kamenev_kur_1.Classes.AuthData
import com.example.kamenev_kur_1.Classes.Users
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("Users/userAuth")
    suspend fun postLoginData(@Body authData: AuthData): Users

    @POST("Users")
    suspend fun regUser(@Body users: Users) : ResponseBody

}