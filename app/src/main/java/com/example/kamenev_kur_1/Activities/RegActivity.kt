package com.example.kamenev_kur_1.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kamenev_kur_1.Api.UserApi
import com.example.kamenev_kur_1.Classes.Users
import com.example.kamenev_kur_1.R
import com.example.kamenev_kur_1.databinding.ActivityRegBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var regBinding: ActivityRegBinding
class RegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regBinding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(regBinding.root)

        val retrofit =Retrofit.Builder()
            .baseUrl("http://192.168.0.7:5257/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val userApi = retrofit.create(UserApi::class.java)

        regBinding.regSubmitBtn.setOnClickListener {
            if(regBinding.regPassBox.text.toString() != regBinding.regConfirmPassBox.text.toString()){
                Toast.makeText(this@RegActivity, "Passwords don`t match",Toast.LENGTH_SHORT).show()
            }
            else{
                CoroutineScope(Dispatchers.IO).launch {
                    val response = userApi.regUser(Users(
                        idUser = 0,
                        nameUser = regBinding.regNameBox.text.toString(),
                        surnameUser = regBinding.regSurnameBox.text.toString(),
                        address = regBinding.regAddressBox.text.toString(),
                        numberUser = regBinding.regPhoneBox.text.toString(),
                        login = regBinding.regLoginBox.text.toString(),
                        password = regBinding.regPassBox.text.toString()
                    ))
                    runOnUiThread{
                        if(response != null){
                            Toast.makeText(this@RegActivity,"You have succsessfully register", Toast.LENGTH_SHORT).show()
                            finish()
                            val intent = Intent(this@RegActivity, AuthActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this@RegActivity, "An error occurred during registration", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}