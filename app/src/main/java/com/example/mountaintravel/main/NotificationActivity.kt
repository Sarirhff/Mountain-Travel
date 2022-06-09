package com.example.mountaintravel.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mountaintravel.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    lateinit var  binding : ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}