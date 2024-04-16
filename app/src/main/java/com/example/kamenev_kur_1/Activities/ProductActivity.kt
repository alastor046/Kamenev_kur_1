package com.example.kamenev_kur_1.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kamenev_kur_1.Api.MainApi
import com.example.kamenev_kur_1.Api.ProductApi
import com.example.kamenev_kur_1.adapters.ProductAdapter
import com.example.kamenev_kur_1.databinding.ActivityProductBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var adapter: ProductAdapter
lateinit var productBinding: ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productBinding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(productBinding.root)

        adapter = ProductAdapter()
        productBinding.rcView.layoutManager = LinearLayoutManager(this)
        productBinding.rcView.adapter = adapter

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.7:5257/api/").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productApi = retrofit.create(ProductApi::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val listProduct = productApi.getAllProducts()
            runOnUiThread {
                productBinding.apply {
                    adapter.submitList(listProduct)
                }

            }
        }

    }
}