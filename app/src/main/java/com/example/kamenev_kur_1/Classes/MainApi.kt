package com.example.kamenev_kur_1.Classes


import okhttp3.ResponseBody
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
    suspend fun getAllProducts1(): List<Product>

    @GET("Products")
    suspend fun getAllProducts(): List<Products>
}