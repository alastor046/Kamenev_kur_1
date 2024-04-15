package com.example.kamenev_kur_1.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.kamenev_kur_1.Classes.MainApi
import com.example.kamenev_kur_1.R
import com.example.kamenev_kur_1.adapters.ProductAdapter
import com.example.kamenev_kur_1.databinding.ActivityProductBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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


        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.7:5257/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(MainApi::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val listProduct = mainApi.getAllProducts1()
            runOnUiThread {
                productBinding.apply {
                    adapter.submitList(listProduct)
                }

            }
        }

    }
}