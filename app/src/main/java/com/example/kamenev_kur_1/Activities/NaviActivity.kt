package com.example.kamenev_kur_1.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kamenev_kur_1.databinding.ActivityNaviBinding

lateinit var naviBinding: ActivityNaviBinding

class NaviActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        naviBinding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(naviBinding.root)

        naviBinding.btnProduct.setOnClickListener {
            val intent = Intent(this@NaviActivity, ProductActivity::class.java)
            startActivity(intent)
        }

        naviBinding.btnProviders.setOnClickListener {
            val intent = Intent(this@NaviActivity, ProviderActivity::class.java)
            startActivity(intent)
        }

        naviBinding.btnUser.setOnClickListener {
            val intent = Intent(this@NaviActivity, UserActivity::class.java)
            startActivity(intent)
        }
    }
}