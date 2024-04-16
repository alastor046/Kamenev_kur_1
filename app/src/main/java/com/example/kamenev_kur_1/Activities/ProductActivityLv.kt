package com.example.kamenev_kur_1.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kamenev_kur_1.Api.ProductApi
import com.example.kamenev_kur_1.R
import com.example.kamenev_kur_1.adapters.ProductAdapter
import com.example.kamenev_kur_1.adapters.ProductAdapterLV
import com.example.kamenev_kur_1.databinding.ActivityProductLvBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var productLvBinding: ActivityProductLvBinding
class ProductActivityLv : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productLvBinding = ActivityProductLvBinding.inflate(layoutInflater)
        setContentView(productLvBinding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.7:5257/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val productApi = retrofit.create(ProductApi::class.java)



        CoroutineScope(Dispatchers.IO).launch {
            val products = productApi.getAllProducts()
            runOnUiThread {
                productLvBinding.lvProduct.adapter =
                    ProductAdapterLV(this@ProductActivityLv, products)
            }
        }

        productLvBinding.lvProduct.setOnItemClickListener { parent, view, position, id ->
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Are you sure, you want to delete?")
                .setPositiveButton("Yes") { dialog, which ->
                    CoroutineScope(Dispatchers.Main).launch {
                        try {
                            productApi.deleteProduct(id)
                        } catch (e: Exception) {
                            Toast.makeText(
                                this@ProductActivityLv,
                                "Point is deleted!",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                            val intent = Intent(this@ProductActivityLv, ProductActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }

        productLvBinding.btnAddLv.setOnClickListener {
            val intent = Intent(this@ProductActivityLv, AddProductActivity::class.java)
            startActivity(intent)
        }


    }
}