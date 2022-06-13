package com.example.mountaintravel.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mountaintravel.R

class DetailMountains : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mountains)

        supportActionBar!!.title = "Mountain Details"
    }
}