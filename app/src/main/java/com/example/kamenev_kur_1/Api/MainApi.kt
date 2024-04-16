package com.example.kamenev_kur_1.Api


import com.example.kamenev_kur_1.Classes.AuthData
import com.example.kamenev_kur_1.Classes.Product
import com.example.kamenev_kur_1.Classes.Users
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {

    @POST("Users/userAuth")
    suspend fun postLoginData(@Body authData: AuthData): Users

    @POST("Users")
    suspend fun regUser(@Body users: Users) : Response<Users>
    

    @GET("Products")
    suspend fun getAllProducts(): List<Product>
}