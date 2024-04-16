package com.example.kamenev_kur_1.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kamenev_kur_1.Api.ProductApi
import com.example.kamenev_kur_1.Classes.Product
import com.example.kamenev_kur_1.databinding.ActivityAddProductBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var addProductBinding: ActivityAddProductBinding
class AddProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addProductBinding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(addProductBinding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.7:5257/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val productApi = retrofit.create(ProductApi::class.java)

        addProductBinding.newProductSubmitBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = productApi.addProduct(
                    Product(
                        idProduct = 0,
                        nameProduct = addProductBinding.newProductNameBox.text.toString(),
                        priceProduct = addProductBinding.newProductPriceBox.text.toString(),
                        typeProduct = addProductBinding.newProductTypeBox.text.toString(),
                        idProvider = 2
                    )
                )
                runOnUiThread {
                    if (response == null){
                        Toast.makeText(this@AddProductActivity,"Was exception, please, try again", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this@AddProductActivity, "The addition is succsessful", Toast.LENGTH_SHORT).show()
                        finish()
                        val intent = Intent(this@AddProductActivity, AddProductActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}