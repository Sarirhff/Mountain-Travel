package com.example.mountaintravel.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mountaintravel.databinding.ActivityBoardingBinding
import com.example.mountaintravel.sign.SignInActivity

class BoardingActivity : AppCompatActivity() {

    lateinit var binding : ActivityBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBoardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSignin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}