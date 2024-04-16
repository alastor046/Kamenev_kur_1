package com.example.kamenev_kur_1.Api

import com.example.kamenev_kur_1.Classes.Product
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApi {
    @GET("Products")
    suspend fun getAllProducts(): List<Product>

    @DELETE("Products/{id}")
    suspend fun deleteProduct(@Path("id") id: Long)

    @POST("Product")
    suspend fun addProduct(@Body product: Product): ResponseBody
}